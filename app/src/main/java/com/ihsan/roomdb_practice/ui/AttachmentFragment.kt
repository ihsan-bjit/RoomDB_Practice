package com.ihsan.roomdb_practice.ui

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.ihsan.roomdb_practice.R
import com.ihsan.roomdb_practice.model.ImageAttachment
import com.ihsan.roomdb_practice.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_attachment.*

class AttachmentFragment : Fragment() {
    val REQUEST_CODE = 100
    private lateinit var viewModel: TodoViewModel
    val args: AttachmentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attachment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        image_view_button.setOnClickListener {
            openGalleryForImage()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            image_view_button.setImageURI(data?.data) // handle chosen image
            if (args.todo != null) {
                val image = ImageAttachment(
                    0,
                    (image_view_button.drawable as BitmapDrawable).bitmap,
                    args.todo!!.id
                )
                viewModel.addImage(image)
            }
        }
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    fun getBitmapeImage() {

    }
}