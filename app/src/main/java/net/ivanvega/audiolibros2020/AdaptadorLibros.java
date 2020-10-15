package net.ivanvega.audiolibros2020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;
// Adaptador personalizado para poder funcionar con la estructura del proyecto
public class AdaptadorLibros extends  RecyclerView.Adapter<AdaptadorLibros.ViewHolder> {

    private  Vector<Libro> vectorLibros;
    private  Context contexto;
    private  LayoutInflater inflador;

    //Desde aqui
    // Se obtienen las acciones de clic y clic largo para asignarlas al Al view que se infla
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public void setOnclickListener(View.OnClickListener onclickListener) {
        this.onclickListener = onclickListener;
    }

    private View.OnLongClickListener onLongClickListener;
    private View.OnClickListener onclickListener;
    // Hasta aqui

    // Constructor para SelectorFragment
    public AdaptadorLibros(Context contexto, Vector<Libro> vectorLibros) {
        //Se infla el contexto que se recibe en el constructor
        inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.vectorLibros = vectorLibros;
        this.contexto = contexto;
    }

    // Se llama cuando un RecyclerWiew necesita un ViewHolder del tipo representado como un item.
    // Se debe de construir con un View que representara los items contenidos en el RecyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el xml elemento selector, que contiene la estructura de la imagen y el titulo
        View v = inflador.inflate(R.layout.elemento_selector, null);
        // Asigna las propiedades on CLick y onClickListener
        v.setOnClickListener(this.onclickListener);
        v.setOnLongClickListener(this.onLongClickListener);

        return new ViewHolder(v);

    }

    // Se crea por cada elemento que se tenga
    // Asigna la portada y el titulo para cada conjunto de elementos
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Libro libro = vectorLibros.get(position);

        holder.portada.setImageResource(libro.recursoImagen);
        holder.titulo.setText(libro.titulo);
    }

    // Retorna el tamaño de los items que se tienen
    @Override
    public int getItemCount() {
        return vectorLibros.size();
    }

    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView portada;
        public TextView titulo;

        public ViewHolder(View itemView)
        {
            super(itemView);
            portada = (ImageView) itemView.findViewById(R.id.portada);
            portada.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            titulo = (TextView) itemView.findViewById(R.id.titulo);
        }
    }

}
