package com.example.perludilindungi.ui.list_faskes

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.perludilindungi.models.Faskes

class ListFaskesFragmentFactory {
    fun newFragment(daftarFaskes: ArrayList<Faskes>, origin: String): Fragment {
        val listfaskesFragment = ListFaskesFragment()
        val args = Bundle()
        args.putSerializable("FASKES", daftarFaskes)
        args.putString("ORIGIN", origin)
        listfaskesFragment.arguments = args
        return listfaskesFragment
    }

}