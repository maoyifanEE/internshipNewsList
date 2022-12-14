package com.example.appproject.ui.home
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appproject.R
import com.example.appproject.ui.User

@SuppressLint("NotifyDataSetChanged")
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        createRecyclerView(view)
        requireActivity().title = "Home"
        return view
    }
    lateinit var viewModel: ViewModel
    private val adapter = Adapter()

    private fun createRecyclerView(view: View):View {

        viewModel = ViewModelProvider(requireActivity()).get(ViewModel::class.java)
        adapter.data.addAll(viewModel.getDatas())
        adapter.notifyDataSetChanged()

        val rv = view.findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = adapter

        
        adapter.setOnItemClickListener {
//            Toast.makeText(activity,"Check the detail",Toast.LENGTH_LONG).show()
            viewModel.news.value = it
            val fragmentManager:FragmentManager = parentFragmentManager
            val transition: FragmentTransaction =fragmentManager.beginTransaction()
            transition.replace(R.id.main_activity_fragment_container, ContentFragment())
                .addToBackStack(null).commit()
//            transition.commit()
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("zyp","HomeFragmentDestroyed")
    }



}
