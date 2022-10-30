package com.belajarandroid.connectsqlserver

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.io.PrintWriter
import java.io.StringWriter
import java.lang.Exception

class DatabaseHelper {
    lateinit var ctx: Context

    private var isDone = false
    private lateinit var rv: RecyclerView
    private lateinit var query: String
    private lateinit var adapter: RecyclerView.Adapter<*>
    private var recordCount: Int = 0
    private var functionType: Int = 0
    private lateinit var records: ArrayList<Any>
    lateinit var connectionClass: ConnectionClass

    inner class SyncData : AsyncTask<String, String, String>() {
        private var message = "No Connection or Windows Firewall, not enough permissions error!"
        lateinit var prog: ProgressDialog

        override fun onPreExecute() {
            records.clear()
            recordCount = 0
            prog = ProgressDialog.show(ctx, "Reading Data....", "Loading.. Please Wait", true)
        }

        override fun doInBackground(vararg p0: String?): String {


            try {
                var myConn = connectionClass?.dbconn()

                if (myConn == null) {
                    isDone = false
                } else {
                    val statement = myConn!!.createStatement()
                    val cursor = statement.executeQuery(query)
                    if (cursor != null) {
                        while (cursor!!.next()) {
                            try {
                                when (functionType) {
                                    1 -> records?.add(
                                        Customer(
                                            cursor!!.getInt("CustomerNo"),
                                            cursor!!.getString("CustomerName")
                                        )
                                    )
                                    2 -> records?.add(
                                        Products(
                                            cursor!!.getInt("ProductID"),
                                            cursor!!.getString("Batrcode"),
                                            cursor!!.getString("ProductName1")
                                        )
                                    )
                                }
                                recordCount++
                            } catch (ex: Exception) {
                                ex.printStackTrace()
                            }
                        }
                        message = "Found $recordCount"
                        isDone = true
                    } else {
                        message = "There is No Record Found"
                        isDone = false
                    }
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                val writer = StringWriter()
                ex.printStackTrace(PrintWriter(writer))
                message = writer.toString()
                isDone = false
            }

            return message
        }


        override fun onPostExecute(result: String?) {
            prog.dismiss()
            Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show()
            if (isDone == false) {

            } else {
                try {
                    rv.adapter = adapter
                } catch (ex: Exception) {

                }
            }
        }
    }

    fun getCustomers(rv: RecyclerView) {
        this.rv = rv
        query = "Select customerNo, CustomerName From Customer"
        records = ArrayList<Customer>() as ArrayList<Any> /* = java.util.ArrayList<kotlin.Any> */
        adapter =
            CustomerAdapter(records as ArrayList<Customer> /* = java.util.ArrayList<com.belajarandroid.connectsqlserver.Customer> */)
        functionType = 1
        SyncData().execute("")
    }

    fun getProducts(rv: RecyclerView) {
        this.rv = rv
        query = "Select * From Products"
        records = ArrayList<Products>() as ArrayList<Any> /* = java.util.ArrayList<kotlin.Any> */
        adapter =
            ProductsAdapter(records as ArrayList<Products> /* = java.util.ArrayList<com.belajarandroid.connectsqlserver.Customer> */)
        functionType = 2
        SyncData().execute("")
    }
}