package com.ramzmania.speedometercomposeview
/**
 * Enum class representing different modes for drawing arcs.
 */
enum class Mode {
    /**
     * Normal mode: Draws a solid arc with a single color.
     */
    NORMAL,
    /**
     * Glow mode: Draws an arc with a glowing effect, potentially with multiple colors.
     */
    GLOW,
    /**
     * Gradient mode: Draws an arc with a gradient effect based on the selected gradient type.
     */
    GRADIENT,
    /**
     * Neon mode: Draws an arc with a neon-like effect using radial gradient.
     */
    NEON
}