package com.belajarandroid.connectsqlserver

import android.os.StrictMode
import android.util.Log
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import kotlin.math.log

class ConnectionClass {
    private val ip = "" //Your Database IP Address + Port
    private val db = "" //Your Database Name
    private val username = "" //Your SQL Server username
    private val password = "" //Your SQL Server Password



    //RESPON DARI DATABASE
    fun dbconn(): Connection {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        var conn: Connection? = null
        var connString: String? = null
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver")
            connString =
                "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$username;password=$password;"
            conn = DriverManager.getConnection(connString)
        } catch (ex: SQLException) {
            ex.message?.let { Log.e("Error", it) }
        } catch (ex1: ClassNotFoundException) {
            ex1.message?.let { Log.e("Error : ", it) }
        } catch (ex2: Exception) {
            ex2.message?.let { Log.e("Error :", it) }
        }
        return conn!!
    }
}