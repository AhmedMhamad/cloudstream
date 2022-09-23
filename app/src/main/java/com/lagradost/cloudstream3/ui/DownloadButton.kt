package com.lagradost.cloudstream3.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.lagradost.cloudstream3.R
import com.lagradost.fetchbutton.aria2c.DownloadStatusTell
import com.lagradost.fetchbutton.aria2c.Metadata
import com.lagradost.fetchbutton.ui.PieFetchButton

class DownloadButton(context: Context, attributeSet: AttributeSet) :
    PieFetchButton(context, attributeSet) {

    var progressText: TextView? = null
    var mainText: TextView? = null
    var bigButton: MaterialButton? = null

    override fun onInflate() {
        overrideLayout = R.layout.download_button_layout
        super.onInflate()
        progressText = findViewById(R.id.result_movie_download_text_precentage)
        mainText = findViewById(R.id.result_movie_download_text)
        bigButton = findViewById(R.id.download_big_button)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        bigButton?.setOnClickListener(l)
    }

    override fun setOnLongClickListener(l: OnLongClickListener?) {
        bigButton?.setOnLongClickListener(l)
    }

    override fun setStatus(status: DownloadStatusTell?) {
        super.setStatus(status)
        val txt = when (status) {
            DownloadStatusTell.Paused -> R.string.download_paused
            DownloadStatusTell.Active -> R.string.downloading
            DownloadStatusTell.Complete -> R.string.downloaded
            else -> R.string.download
        }
        mainText?.setText(txt)
    }

    override fun updateViewOnDownload(metadata: Metadata) {
        super.updateViewOnDownload(metadata)

        val isVis = metadata.progressPercentage > 0
        progressText?.isVisible = isVis
        if (isVis)
            progressText?.text = "${metadata.progressPercentage}%"
    }
}