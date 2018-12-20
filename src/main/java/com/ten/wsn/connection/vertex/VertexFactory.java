package com.ten.wsn.connection.vertex;

/**
 * Vertex Factory
 */
public class VertexFactory {
    /**
     * Create random vertex array
     *
     * @param size vertex size
     */
    public static Vertex[] createVertexSet(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Vertex size must be positive!");
        }

        Vertex[] vertices = new Vertex[size];

        for (int i = 0; i < size; i++) {
            vertices[i] = new Vertex(i);
        }

        return vertices;
    }

}
