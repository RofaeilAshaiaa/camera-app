package rofaeil.ashaiaa.idea.cameraapp.gallery;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import rofaeil.ashaiaa.idea.cameraapp.R;
import rofaeil.ashaiaa.idea.cameraapp.data.Image;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private ArrayList<Image> mImages;
    private Context mContext;

    public GalleryAdapter(ArrayList<Image> images, Context context) {
        mImages = images;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.gallery_list_item, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Picasso.with(mContext)
                .load(mImages.get(position).getImageUri())
                .placeholder(R.drawable.ic_image_orange_200_24dp)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {

        if (mImages != null)
          return  mImages.size();

        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_gallery_list_item);
        }
    }
}
