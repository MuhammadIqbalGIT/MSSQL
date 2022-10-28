package com.belajarandroid.connectsqlserver

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomerAdapter(val customers: ArrayList<Customer>) :
    RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtCustomerNo = itemView.findViewById(R.id.txtCostumerNo) as TextView
        val txtCustomerName = itemView.findViewById(R.id.txtCostumerName) as TextView
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.lo_customers, parent, false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val customer: Customer = customers[position]
        holder?.txtCustomerNo?.text = customer.CustomerNo.toString()
        holder?.txtCustomerName?.text = customer.CustomerName
    }
    override fun getItemCount(): Int {
        return customers.size
    }
}



class ProductsAdapter(val products: ArrayList<Products>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtBarcode = itemView.findViewById(R.id.txtBarcode) as TextView
        val txtProductName = itemView.findViewById(R.id.txtProductsID) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.lo_products, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val products: Products = products[position]
        holder?.txtBarcode?.text = products.Barcode
        holder?.txtProductName?.text = products.productName
    }

    override fun getItemCount(): Int {
        return products.size
    }
}
