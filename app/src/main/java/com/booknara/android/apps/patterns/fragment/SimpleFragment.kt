package com.booknara.android.apps.patterns.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.booknara.android.apps.patterns.R
import com.booknara.android.apps.patterns.databinding.FragmentSimpleBinding

class SimpleFragment: Fragment(R.layout.fragment_simple) {

  private lateinit var binding: FragmentSimpleBinding
  private lateinit var text: String

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    text = arguments?.getString(BODY) ?: ""
    Log.i(TAG, "text: $text")
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentSimpleBinding.bind(view)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    if (!TextUtils.isEmpty(text)) {
      binding.simpleText.text = text
    }
  }

  companion object {
    const val TAG = "SimpleFragment"
    const val BODY = "BODY"

    @JvmStatic
    fun newInstance(): Fragment {
      return SimpleFragment()
    }

    @JvmStatic
    fun newInstance(body: String): Fragment {
      val bundle = Bundle()
      bundle.putString(BODY, body)
      val fragment = SimpleFragment()
      fragment.arguments = bundle
      return fragment
    }
  }
}
