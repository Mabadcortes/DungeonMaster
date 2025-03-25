package adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectointegrado.R;

import java.io.IOException;
import java.util.List;

import Controllers.ApiController;
import data.EquipmentListData;

public class EquipmentListAdapter extends ArrayAdapter<EquipmentListData> {

    private List<EquipmentListData> equipmentList;

    public EquipmentListAdapter(Context context, List<EquipmentListData> equipmentList) {
        super(context, R.layout.equipment_list, equipmentList);
        this.equipmentList = equipmentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View elemento = mostrado.inflate(R.layout.equipment_list, parent, false);

        TextView equipmentName = elemento.findViewById(R.id.equipmentName);
        equipmentName.setText(equipmentList.get(position).getName());

        TextView equipmentCategory = elemento.findViewById(R.id.equipmentCategory);
        equipmentCategory.setText(equipmentList.get(position).getEquipmentCategoryName());

        TextView cost = elemento.findViewById(R.id.cost);
        cost.append(String.valueOf(equipmentList.get(position).getQuantity()));
        cost.append(" ");
        cost.append(equipmentList.get(position).getUnit());

        TextView weight = elemento.findViewById(R.id.weightText);
        weight.append(String.valueOf(equipmentList.get(position).getWeight()));

        TextView gearCategory = elemento.findViewById(R.id.gearCategory);
        gearCategory.setText(equipmentList.get(position).getGearCategoryName());


        return elemento;
    }


}
