package com.`kevin-nation`.minecraft.data

import net.minecraft.util.ResourceLocation

class ToggleableResourceLocation(
    primary: String,
    secondary: String,
): ResourceLocation(primary) {
    private var useSecondary = false
    private val secondaryResource = ResourceLocation(secondary)

    fun primary() {
        useSecondary = false
    }

    fun secondary() {
        useSecondary = true
    }

    override fun compareNamespaced(o: ResourceLocation): Int = when {
        useSecondary -> secondaryResource.compareNamespaced(o)
        else -> super.compareNamespaced(o)
    }

    override fun compareTo(other: ResourceLocation): Int = when {
        useSecondary -> secondaryResource.compareTo(other)
        else -> super.compareTo(other)
    }

    override fun equals(other: Any?): Boolean = when {
        useSecondary -> secondaryResource.equals(other)
        else -> super.equals(other)
    }

    override fun getNamespace(): String = when {
        useSecondary -> secondaryResource.namespace
        else -> super.getNamespace()
    }

    override fun getPath(): String = when {
        useSecondary -> secondaryResource.path
        else -> super.getPath()
    }

    override fun hashCode(): Int = when {
        useSecondary -> secondaryResource.hashCode()
        else ->  super.hashCode()
    }

    override fun toString(): String = when {
        useSecondary -> secondaryResource.toString()
        else -> super.toString()
    }
}