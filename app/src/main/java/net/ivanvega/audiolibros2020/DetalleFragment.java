package net.ivanvega.audiolibros2020;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
// Es un Fragmento donde se muestran los detalles del libro seleccionado
public class DetalleFragment extends Fragment {
    public static String ARG_ID_LIBRO = "id_libro";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleFragment newInstance(String param1, String param2) {
        DetalleFragment fragment = new DetalleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    // Vincular con los parametros de fragmento
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // Se crea cada que se crea el View
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View vista = inflater.inflate(R.layout.fragment_detalle, container, false);
        // Bundle permite el intercambio de Objetos con la aplicacion
        Bundle args = getArguments();

        // Validar si hay un elemento seleccionado
        if (args != null) {
            // Si existe un elemento seleccionado, pone ese libro
            int position = args.getInt(ARG_ID_LIBRO);
            ponInfoLibro(position, vista);
        } else {
            // Pone el libro con id 0
            ponInfoLibro(0, vista);
        }


        return vista;
    }
    // Metodo que obtiene el view del fragment actual y llama a su metodo sobrecargado indicandole el indice que se le agrega
    public void ponInfoLibro(int id) {
        ponInfoLibro(id, getView());
    }

    // metodo privado que obtiene la vista actual con el metodo sobrecargado publico y el id, los coloca en el fragment actual
    private void ponInfoLibro(int id, View vista) {
        // Se busca el id del libro en el vector libros y se guarda este libro
        Libro libro = Libro.ejemploLibros().elementAt(id);
        // A la vista actual se le asignan a los textview y al imageview los valores que el libro tiene
        ((TextView) vista.findViewById(R.id.titulo)).setText(libro.titulo);
        ((TextView) vista.findViewById(R.id.autor)).setText(libro.autor);
        ((ImageView) vista.findViewById(R.id.portada)).setImageResource(libro.recursoImagen);
    }
}