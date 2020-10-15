package net.ivanvega.audiolibros2020;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
/*
* Classe que extiende de fragment para mostrar todos los libros
* Es una implementación de un fragmento
* */
// clase que hereda de fragment y es la encargada de mostrar todos los libros para seleccionarlos
public class SelectorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recycler;
    private GridLayoutManager layoutManager;

    MainActivity mainActivity;
    Context context;

    public SelectorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectorFragment newInstance(String param1, String param2) {
        SelectorFragment fragment = new SelectorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /*
    * Se ejecuta cuando el fragmento se adjunta a la actividad
    * Se evalua que el contexto sea una instancia de la actividad principal
    * para que solamente se implemente en esa actividad
    * */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if(context instanceof MainActivity){
            mainActivity = (MainActivity) context;
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_selector, container, false);
        //Se obtiene el reciclerView creado en el fragment selector
        recycler =   (RecyclerView)v.findViewById(R.id.recyclerView);

        // Se crea un GridLayoutManager para establecer a dos columnas los libros
        layoutManager = new GridLayoutManager(getActivity(),2);
        // Se le asigna el GridLayoutManager al recycler
        recycler.setLayoutManager(layoutManager);
        // Se inicia una instancia de AdaptadorLibros a la cual se le pasa la actividad del Fragment y el vector de libros
        AdaptadorLibros adaptadorLibros = new AdaptadorLibros(getActivity() , Libro.ejemploLibros());
            // Se asigna el comportamiento del click
            adaptadorLibros.setOnclickListener(
                    vl -> {
                        Toast.makeText(getActivity(), "Elemento seleccionado: " + recycler.getChildAdapterPosition(vl) , Toast.LENGTH_LONG).show();
                        // Se llama al metodo mostrar  detalle y se le manda el id del elemento cliqueado
                        mainActivity.mostrarDetalle(recycler.getChildAdapterPosition(vl));
                    }
            );
            adaptadorLibros.setOnLongClickListener(
                    view -> {
                        //Creando cuadro de dialogo
                        AlertDialog.Builder cuadroDialogo = new AlertDialog.Builder(context);
                        cuadroDialogo.setTitle("Seleccionar la opción");
                        //cuadroDialogo.setMessage("Este es un cuadro de diálogo");
                        cuadroDialogo.setItems(
                                new String[]{"Compartir", "Eliminar", "Agregar"},
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(getActivity(),"Opción seleccionada: "+ i,Toast.LENGTH_LONG).show();
                                    }
                                }
                        );
                        cuadroDialogo.setPositiveButton("Ok",
                                (dialogInterface, i) -> {

                                }
                        );
                        cuadroDialogo.create().show();
                        return false;
                    }
            );

        // Se le asigna el adaptador creado al recycler y esto hace que tome la parte del fragment selector
        recycler.setAdapter(adaptadorLibros);

        return v;
    }
}