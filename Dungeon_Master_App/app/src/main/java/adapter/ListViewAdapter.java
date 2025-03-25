package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectointegrado.R;

import java.util.List;

import data.ListViewData;

public class ListViewAdapter extends ArrayAdapter<ListViewData> {

    private List<ListViewData> characterList;

    public ListViewAdapter(Context context, List<ListViewData> characterList) {
        super(context, R.layout.element_list, characterList);
        this.characterList = characterList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View elemento = mostrado.inflate(R.layout.element_list, parent, false);

        TextView name = elemento.findViewById(R.id.name);
        name.append(characterList.get(position).getName());

        TextView className = elemento.findViewById(R.id.className);
        className.append(characterList.get(position).getCharacterClass());

        TextView level = elemento.findViewById(R.id.level);
        level.append(String.valueOf(characterList.get(position).getLevel()));

        TextView race = elemento.findViewById(R.id.race);
        race.append(characterList.get(position).getRace());

        return elemento;
    }
}
