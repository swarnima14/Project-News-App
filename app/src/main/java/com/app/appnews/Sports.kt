package com.app.appnews

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.fragment_sports.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Sports : Fragment() {

    lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Article>()
    var pageNum = 1
    var totalResults = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_sports, container, false)

        adapter = NewsAdapter(requireContext(), articles)
        v.sportsList.adapter = adapter

        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)
        layoutManager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
            override fun onItemChanged(position: Int) {

                // conSports!!.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))

                if (totalResults > layoutManager.itemCount && layoutManager.getFirstVisibleItemPosition() >= layoutManager.itemCount - 5) {
                    //load data of next page
                    pageNum++
                    getNews()
                }
                if(position%5 == 0)
                {
                }
            }

        })
        v.sportsList.layoutManager = LinearLayoutManager(requireContext())
        getNews()

        return v
    }

    private fun getNews() {
        val news: Call<News> = NewsService.newsInstance.getHeadlines("in",pageNum, "sports")
        news.enqueue(object: Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news: News? = response.body()
                if(news!=null)
                {
                    totalResults = news.totalResults
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(requireContext(),"Error: ${t.message}", Toast.LENGTH_SHORT)
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menuSignOut){
            Firebase.auth.signOut()
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()
        }
        return true
    }

}