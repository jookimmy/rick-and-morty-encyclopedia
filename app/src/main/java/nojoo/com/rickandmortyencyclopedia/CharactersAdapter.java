package nojoo.com.rickandmortyencyclopedia;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.*;

public class CharactersAdapter extends ArrayAdapter<Item> {
    private Context mContext;
    private int resourceId;
    ArrayList<Item> data;

    public CharactersAdapter(Context context, int layoutResourceId, ArrayList<Item> data)
    {
        super(context, layoutResourceId, data);
        this.mContext = context;
        this.resourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View itemView = convertView;
        ViewHolder holder = null;

        if (itemView == null)
        {
            final LayoutInflater layoutInflater =
                    (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = layoutInflater.inflate(resourceId, parent, false);

            holder = new ViewHolder();
            holder.imgItem = (ImageView) itemView.findViewById(R.id.imgItem);
            holder.txtItem = (TextView) itemView.findViewById(R.id.txtItem);
            holder.item_id= (TextView) itemView.findViewById(R.id.item_id);
            itemView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) itemView.getTag();
        }

        Item item = getItem(position);
        Picasso.get().load(item.getImage()).into(holder.imgItem);
        holder.txtItem.setText(item.getTitle());
        holder.item_id.setText(item.getID());

        return itemView;
    }

    static class ViewHolder
    {
        ImageView imgItem;
        TextView txtItem;
        TextView item_id;
    }

}
