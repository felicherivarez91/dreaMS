package com.healios.sourcetracker

import java.io.*
import java.util.zip.GZIPOutputStream


fun TrackerService.toCSVStream(): OutputStream {

    val output = ByteArrayOutputStream()

    output.use {
        val writer = OutputStreamWriter(GZIPOutputStream(output), "UTF-8")
        writer.use {
            writer.append(getSourceMagnitudeNames())
            tracks.forEach {track ->
                writer.append(track.joinToString(separator = ";") )
            }
        }
    }

    return output

}