package net.iesseveroochoa.germanbeldamolina.practica4.practica4;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class PoblacionesAdapter extends ArrayAdapter<Poblacion> {
    private List<Poblacion> poblacionesValoradas;

    public PoblacionesAdapter(Context context, int resource, List<Poblacion> lista_poblaciones) {
        super(context, resource, lista_poblaciones);
        poblacionesValoradas = lista_poblaciones;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

    View item = convertView;
    ViewHolder holder;

    if (item == null){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        item = inflater.inflate(R.layout.item_poblacion,null);

        holder = new ViewHolder(); // Enlace a la variables de la clase ViewHolder
        holder.localidad = (TextView) item.findViewById(R.id.tv_localidad);
        holder.provincia = (TextView) item.findViewById(R.id.tv_provincia);
        holder.valoracion = (RatingBar) item.findViewById(R.id.rb_valoracion);
        holder.comentarios = (TextView) item.findViewById(R.id.tv_comentarios);
        item.setTag(holder);
        }else{
        holder = (ViewHolder) item.getTag();
        }
        holder.localidad.setText(this.poblacionesValoradas.get(position).getLocalidad());
        holder.provincia.setText(this.poblacionesValoradas.get(position).getProvincia());
        holder.valoracion.setRating(this.poblacionesValoradas.get(position).getValoracion());
        holder.comentarios.setText(this.poblacionesValoradas.get(position).getComentarios());

        return item;
    }

    /**
     * Devolvemos el listado de poblaciones para el momento de girar la pantalla.
     * @return poblacionesValoradas
     */
    public List<Poblacion> getPoblacionesValoradas() {
        return poblacionesValoradas;
    }

    static class ViewHolder {
        private TextView provincia;
        private TextView localidad;
        private RatingBar valoracion;
        private TextView comentarios;
    }

    public void addPoblacion(Poblacion p){
        this.poblacionesValoradas.add(p);
        notifyDataSetChanged();
    }

    public void delPoblacion (int index){
        this.poblacionesValoradas.remove(index);
        notifyDataSetChanged();
    }
    public void editarLocalidad(Poblacion p){
        poblacionesValoradas.get(poblacionesValoradas.indexOf(p)).setValoracion(p.getValoracion());
        poblacionesValoradas.get(poblacionesValoradas.indexOf(p)).setComentarios(p.getComentarios());
        notifyDataSetChanged();
    }
}
