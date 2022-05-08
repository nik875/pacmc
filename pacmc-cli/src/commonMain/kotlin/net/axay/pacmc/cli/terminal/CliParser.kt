package net.axay.pacmc.cli.terminal

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.ParameterHolder
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.defaultLazy
import com.github.ajalt.clikt.parameters.options.defaultLazy
import com.github.ajalt.clikt.parameters.options.option
import net.axay.pacmc.app.features.Archive
import net.axay.pacmc.cli.terminal

fun ParameterHolder.archiveIdOption(
    help: String
) = option("-a", "--archive", help = help)
    .defaultLazy { Archive.getDefault() }

fun CliktCommand.archiveIdArgument(
    help: String
) = argument("archiveIdentifier", help = help)
    .defaultLazy { Archive.getDefault() }

suspend fun Archive.Companion.fromString(name: String?): Archive? {
    val archive = Archive(name ?: getDefault())
    return if (archive.exists()) archive else {
        terminal.warning("The given archive '${archive.name}' does not exist")
        null
    }
}
