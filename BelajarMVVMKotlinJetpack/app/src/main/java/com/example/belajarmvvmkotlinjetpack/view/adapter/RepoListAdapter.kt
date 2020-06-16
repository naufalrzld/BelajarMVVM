package com.example.belajarmvvmkotlinjetpack.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.belajarmvvmkotlinjetpack.BR
import com.example.belajarmvvmkotlinjetpack.R
import com.example.belajarmvvmkotlinjetpack.databinding.ListItemRepoBinding
import com.example.belajarmvvmkotlinjetpack.model.Item
import com.example.belajarmvvmkotlinjetpack.view.ui.repolist.RepoListViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_repo.view.*

class RepoListAdapter(viewModel: RepoListViewModel) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {
    var repoList: List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ListItemRepoBinding.inflate(inflater, parent, false)

        return ViewHolder(dataBinding)
    }

    override fun getItemCount() = repoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setup(repoList[position])
    }

    class ViewHolder(private val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {
        fun setup(itemData: Item) = with(itemView) {
            dataBinding.setVariable(BR.itemData, itemData)
            dataBinding.executePendingBindings()

            Picasso.get().load(itemData.owner.avatar_url).into(item_avatar)

            this.setOnClickListener {
                val bundle = bundleOf("url" to itemData.html_url)
                itemView.findNavController().navigate(R.id.action_repoListFragment_to_repoDetailFragment, bundle)
            }
        }
    }

    fun updateRepoList(repoList: List<Item>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }
}