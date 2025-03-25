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

import data.SpellListData;

public class SpellListAdapter extends ArrayAdapter<SpellListData> {
    private List<SpellListData> spellList;

    public SpellListAdapter(@NonNull Context context, List<SpellListData> spellList) {
        super(context, R.layout.spell_list, spellList);
        this.spellList = spellList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View elemento = mostrado.inflate(R.layout.spell_list, parent, false);

        TextView spellName = elemento.findViewById(R.id.spellName);
        spellName.setText(spellList.get(position).getName());

        TextView range = elemento.findViewById(R.id.range);
        range.append(spellList.get(position).getRange());

        TextView castingType = elemento.findViewById(R.id.castingType);
        castingType.append(spellList.get(position).getCastingType());

        TextView schoolType = elemento.findViewById(R.id.school);
        schoolType.append(spellList.get(position).getSchool());

        TextView description = elemento.findViewById(R.id.description);
        description.setText(spellList.get(position).getDescription());

        return elemento;
    }
}
