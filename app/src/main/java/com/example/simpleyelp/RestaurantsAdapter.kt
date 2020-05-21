package com.example.simpleyelp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_restaurant.view.*
private const val TAG = "MainActivity"

class RestaurantsAdapter(val context: Context, val restaurants: List<YelpRestaurant>) :
    RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false))
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant: YelpRestaurant) {
            itemView.tvName.text = restaurant.name
            when(restaurant.rating.toFloat()) {
                in 0.0..0.49 -> itemView.yelpStars.setImageDrawable(getDrawable(context,R.drawable.stars_small_0))
                in 0.5..1.24 -> itemView.yelpStars.setImageDrawable(getDrawable(context,R.drawable.stars_small_1))
                in 1.25..1.74 -> itemView.yelpStars.setImageDrawable(getDrawable(context,R.drawable.stars_small_1_half))
                in 1.75..2.24 -> itemView.yelpStars.setImageDrawable(getDrawable(context,R.drawable.stars_small_2))
                in 2.25..2.74 -> itemView.yelpStars.setImageDrawable(getDrawable(context,R.drawable.stars_small_2_half))
                in 2.75..3.24 -> itemView.yelpStars.setImageDrawable(getDrawable(context,R.drawable.stars_small_3))
                in 3.25..3.74 -> itemView.yelpStars.setImageDrawable(getDrawable(context,R.drawable.stars_small_3_half))
                in 3.75..4.24 -> itemView.yelpStars.setImageDrawable(getDrawable(context,R.drawable.stars_small_4))
                in 4.25..4.74 -> itemView.yelpStars.setImageDrawable(getDrawable(context,R.drawable.stars_small_4_half))
                in 4.75..5.0 -> itemView.yelpStars.setImageDrawable(getDrawable(context,R.drawable.stars_small_5))
            }
            itemView.tvNumReviews.text = "${restaurant.numReviews} Reviews"
            itemView.tvAddress.text = restaurant.location.address
            itemView.tvCategory.text = restaurant.categories[0].title
            itemView.tvDistance.text = restaurant.displayDistance()
            itemView.tvPrice.text = restaurant.price
            Glide.with(context).load(restaurant.imageUrl).apply(RequestOptions().transforms(
                CenterCrop(), RoundedCorners(20)
            )).into(itemView.imageView)
            itemView.yelpLogo.setImageDrawable(getDrawable(context,R.drawable.yelp_logo))
        }
    }
}
