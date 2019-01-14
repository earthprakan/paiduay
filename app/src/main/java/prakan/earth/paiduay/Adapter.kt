package prakan.earth.paiduay

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import android.support.v7.app.AlertDialog

class Adapter(var data: List<Events>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.recycler_adapter, p0, false))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.findViewById(R.id.recycler_adapter_id)
        var type: TextView = itemView.findViewById(R.id.recycler_adapter_type)
        var actorid: TextView = itemView.findViewById(R.id.recycler_adapter_actorid)
        var actorlogin: TextView = itemView.findViewById(R.id.recycler_adapter_actorlogin)
        var actordisplaylogin: TextView = itemView.findViewById(R.id.recycler_adapter_actordisplaylogin)
        var actorgravatarid: TextView = itemView.findViewById(R.id.recycler_adapter_actorgravatarid)
        var actorurl: TextView = itemView.findViewById(R.id.recycler_adapter_actorurl)
        var public: TextView = itemView.findViewById(R.id.recycler_adapter_public)
        var createdat: TextView = itemView.findViewById(R.id.recycler_adapter_createdat)
        var repo: TextView = itemView.findViewById(R.id.recycler_adapter_repo)
        var payload: TextView = itemView.findViewById(R.id.recycler_adapter_payload)
        var avartar_url: ImageView = itemView.findViewById(R.id.recycler_adapter_avartar_url)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).also {
            it.id.text = "id : " + data[position].id
            it.type.text = "type : " + data[position].type
            it.actorid.text = "actor_id : " + data[position].actor["id"]
            it.actorlogin.text = "actor_login : " + data[position].actor["login"]
            it.actordisplaylogin.text = "actor_display_login : " + data[position].actor["display_login"]
            it.actorgravatarid.text = "actor_gravatar_id : " + if (data[position].actor["gravatar_id"].isNullOrBlank()) "No data" else data[position].actor["gravatar_id"]
            it.actorurl.text = "actor_url : " + data[position].actor["url"]
            it.public.text = "public : " + data[position].public.toString()
            it.createdat.text = "created_at : " + data[position].created_at
            Glide.with(it.itemView.context).load(data[position].actor["avatar_url"]).into(it.avartar_url)
            it.repo.setOnClickListener { _ ->
                val builder: AlertDialog.Builder =
                    AlertDialog.Builder(it.itemView.context, android.R.style.Theme_Material_Dialog_Alert)
                builder.setTitle("Repo")
                    .setMessage(data[position].repo.toString())
                    .setPositiveButton("Close") { _, _ -> }
                    .show()
            }
            it.payload.setOnClickListener { _ ->
                val builder: AlertDialog.Builder =
                    AlertDialog.Builder(it.itemView.context, android.R.style.Theme_Material_Dialog_Alert)
                builder.setTitle("Payload")
                    .setMessage(data[position].payload.toString())
                    .setPositiveButton("Close") { _, _ -> }
                    .show()
            }
        }
    }

    override fun getItemCount(): Int = data.size

}