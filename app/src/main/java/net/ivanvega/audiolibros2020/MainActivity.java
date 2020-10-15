package net.ivanvega.audiolibros2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Nueva instancia del SelectorFragment
        SelectorFragment selectorFragment = new SelectorFragment();

        // Valida que se haya cargado el activity main normal buscando que contenedor_pequeno exista, si no existe quiere decir que se cargo
        // el de 600 dp porque este no contiene ese contenedor. con getSupportFragmentManager revisa que no se haya cargado aun un fragment en
        // contenedor_pequeno para evitar la sobrecarga de fragmentos.
        if ( findViewById(R.id.contenedor_pequeno) != null && getSupportFragmentManager().findFragmentById(R.id.contenedor_pequeno) == null ){
            // Agrega al contenedor_pequeno el selectorFragment inicializado anteriormente
            getSupportFragmentManager().beginTransaction().add(R.id.contenedor_pequeno, selectorFragment).commit();
        }

    }

    // Muestra los detalles de los libros en el fragmento detalle_fragment
    public void mostrarDetalle(int index){
        // Se crea una nueva instancia de un FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Se revisa si existe detalle_fragment (si se cargo la pantalla de 600dp)
        if(fragmentManager.findFragmentById(R.id.detalle_fragment)!=null){
            // Se vincula la clase detalleFragment con el Fragment en el activity_main
            DetalleFragment fragment = (DetalleFragment) fragmentManager.findFragmentById(R.id.detalle_fragment);
            // Se llama a la clase ponInfoLibros y se le da el indice del elemento seleccionado
            fragment.ponInfoLibro(index);

        }else{
            // Se crea una nueva instancia de DetalleFragment
            DetalleFragment detalleFragment = new DetalleFragment();
            // Se crea una nueva instancia de Bundle para la comunicacion entre Fragment y Layout
            Bundle bundle = new Bundle();
            // Se coloca en la variable de clase ARG_ID_LIBRO el valor index que se tiene en este metodo
            bundle.putInt(DetalleFragment.ARG_ID_LIBRO, index);
            // Se agrega el bundle creado anteriormente al detalle fragment. Esto para comunicar el fragment y el activity actual
            detalleFragment.setArguments(bundle);

            //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            // Se inicia la transaccion remplazanddo contenedor pequeño por detalle fragment
            fragmentManager.beginTransaction().replace(R.id.contenedor_pequeno, detalleFragment).addToBackStack(null).commit();

        }

    }
}