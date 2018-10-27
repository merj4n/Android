package net.iesseveroochoa.germanbeldamolina.practica4.practica4;

import android.os.Parcel;
import android.os.Parcelable;

public class Poblacion implements Parcelable {
    private String provincia;
    private String localidad;
    private float valoracion;
    private String comentarios;

    public Poblacion(String provincia, String localidad, float valoracion, String comentarios) {
        this.provincia = provincia;
        this.localidad = localidad;
        this.valoracion = valoracion;
        this.comentarios = comentarios;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Poblacion poblacion = (Poblacion) o;

        if (!provincia.equals(poblacion.provincia)) return false;
        return localidad.equals(poblacion.localidad);
    }

    @Override
    public int hashCode() {
        int result = provincia.hashCode();
        result = 31 * result + localidad.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.provincia);
        dest.writeString(this.localidad);
        dest.writeFloat(this.valoracion);
        dest.writeString(this.comentarios);
    }

    protected Poblacion(Parcel in) {
        this.provincia = in.readString();
        this.localidad = in.readString();
        this.valoracion = in.readFloat();
        this.comentarios = in.readString();
    }

    public static final Parcelable.Creator<Poblacion> CREATOR = new Parcelable.Creator<Poblacion>() {
        @Override
        public Poblacion createFromParcel(Parcel source) {
            return new Poblacion(source);
        }

        @Override
        public Poblacion[] newArray(int size) {
            return new Poblacion[size];
        }
    };
}
