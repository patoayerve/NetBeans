package Clases;
import java.util.*;

public class Busqueda
{
    public enum NodeColour { WHITE, GRAY, BLACK }
    /*Los nodos son implementados por clases, estructuras o como nodos Link-List. */
    public static class Node
    {
        String data;
        int distancia;
        Node predecesor;
        NodeColour color;
        
        public Node(String data)
        {
            this.data = data;
        }
        
        public String toString()
        {
            return "(" + data + ",d=" + distancia + ")";
        }
    }
    
    Map<Node, List<Node>> nodes;
    public Busqueda()
    {
        nodes = new HashMap<>();
    }
    /**/
    public void conexion(Node n1, Node n2)
    {
        if (nodes.containsKey(n1)) {
            nodes.get(n1).add(n2);
        } else {
            ArrayList<Node> list = new ArrayList<>();
            list.add(n2);
            nodes.put(n1, list);
        }
    }
    
    public void b_amplitud(Node s)
    {
        Set<Node> keys = nodes.keySet();
        for (Node u : keys) {
            if (u != s) {
                u.color = NodeColour.WHITE;
                u.distancia = Integer.MAX_VALUE;
                u.predecesor = null;
            }
        }
        s.color = NodeColour.GRAY;
        s.distancia = 0;
        s.predecesor = null;
        Queue<Node> q = new ArrayDeque<>();
        q.add(s);
        while (!q.isEmpty()) {
            Node u = q.remove();
            List<Node> adj_u = nodes.get(u);
            if (adj_u != null) {
                for (Node v : adj_u) {
                    if (v.color == NodeColour.WHITE) {
                        v.color = NodeColour.GRAY;
                        v.distancia = u.distancia + 1;
                        v.predecesor = u;
                        q.add(v);
                    }
                }
            }
            u.color = NodeColour.BLACK;
            System.out.print(u + " ");
        }
    }
    
    public static void main(String[] args)
    {
        Busqueda grafo = new Busqueda();
        Node n1 = new Node("I");
        Node n2 = new Node("A");
        Node n3 = new Node("B");
        Node n4 = new Node("D");
        Node n5 = new Node("C");
        Node n6 = new Node("E");
        Node n7 = new Node("F");
        Node n8 = new Node("G");
        /*PRIMERA CONEXION (I A I D)*/
        grafo.conexion(n1, n2);
        grafo.conexion(n1, n4);
        /*SEGUNDA CONEXION (A B A D)*/
        grafo.conexion(n2, n3);
        grafo.conexion(n2, n4);
        /*TERCERA CONEXION (B C B E)*/
        grafo.conexion(n3, n5);
        grafo.conexion(n3, n6);
        /*CUARTA CONEXION (D E)*/
        grafo.conexion(n4, n6);
        /*QUINTA CONEXION (E F)*/                
        grafo.conexion(n6, n7);
        /*SEXTA CONEXION (F G)*/
        grafo.conexion(n7, n8);
        /*EJECUCION DE LA BUSQUEDA CON EL METODO b_amplitud*/
        grafo.b_amplitud(n5);
    }
}