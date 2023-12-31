package com.tll.pexelsapp.photoSettings.usecase

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import com.tll.pexelsapp.photoSettings.tools.PhotoUrlGenerator
import com.tll.pexelsapp.photoSettings.tools.ResolutionManager
import java.io.File
import java.net.URI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoDownloadingUseCase @Inject constructor(
    private val context: Context ,
    private val resolutionManager: ResolutionManager
) {
    private val linkGenerator = PhotoUrlGenerator()

    fun callManagerToDownloadPhotoByFLow(
        author: String,
        postfix: String,
        baseLink: String,
        originalResolution: Pair<Int, Int>? = null
    ): Flow<Int> {
        val fileName = "${author.replace(" ", "_")}_$postfix.jpeg"
        val downloadUrl = Uri.parse(linkGenerator.generateUrl(
            baseLink, if (originalResolution != null) ResolutionManager.Resolution(originalResolution.first,
                originalResolution.second)
            else resolutionManager.screenResolution
        )
        )
        val photoDirPath = Environment.getExternalStorageDirectory().toString() + "/PexWalls"
        val photoDir = File(photoDirPath)
        if (photoDir.exists().not()) photoDir.mkdirs()
        val photoFile = File(photoDir, fileName)

        val downloadRequest = DownloadManager.Request(downloadUrl)
            .setTitle(fileName)
            .setDescription("downloading photo")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationUri(Uri.fromFile(photoFile))
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val id = downloadManager.enqueue(downloadRequest)
        return buildProgressFlow(id)
    }

    @SuppressLint("Range")
    private fun buildProgressFlow(
        downloadId: Long
    ): Flow<Int> {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val query = DownloadManager.Query().apply {
            setFilterById(downloadId)
        }
        var cursor: Cursor? = null
        return flow {
            var progress = 0
            emit(progress)
            while (progress <= 100) {
                cursor = downloadManager.query(query)
                cursor!!.moveToFirst()
                val bytesDownloaded = cursor!!.getInt(cursor!!.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                val bytesTotal = cursor!!.getInt(cursor!!.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                progress = (bytesDownloaded * 100L / bytesTotal).toInt()
                if (progress == 100) {
                    cursor?.close()
                }
                emit(progress)
            }
        }
    }
}
