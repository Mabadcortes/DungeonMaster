package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectointegrado.R;

import java.util.ArrayList;

import data.SpinnerData;

public class SpinnerAdapter extends BaseAdapter {

    private ArrayList<SpinnerData> data;
    private Context context;

    public SpinnerAdapter(Context context, ArrayList<SpinnerData> data) {
        super();
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mostrado = LayoutInflater.from(context);
        View elemento = mostrado.inflate(R.layout.spinner_element, parent, false);

        ImageView icon = elemento.findViewById(R.id.icon);
        icon.setImageResource(data.get(position).getIcon());

        TextView spinnerName = elemento.findViewById(R.id.spinnerName);
        spinnerName.setText(data.get(position).getName());

        return elemento;
    }

}
