package prakan.earth.paiduay

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import android.support.v7.widget.DividerItemDecoration
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    interface GitHubService {
        @GET("events")
        fun getEvents(): Call<List<Events>>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.activity_main_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GitHubService::class.java)

        service.getEvents().enqueue(object : Callback<List<Events>> {
            override fun onResponse(call: Call<List<Events>>, response: Response<List<Events>>) {
                recyclerView.adapter = Adapter(response.body()!!)
            }

            override fun onFailure(call: Call<List<Events>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "onFailure : ${t.message}", Toast.LENGTH_LONG).show()
            }
        })

    }

}