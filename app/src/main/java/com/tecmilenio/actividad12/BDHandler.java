package com.tecmilenio.actividad12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BDHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "libraryDB.db";

    //Tabla de usuarios
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID_USERS = "id";
    private static final String COLUMN_NAME_USERS = "user_name";
    private static final String COLUMN_EMAIL_USERS = "email";
    private static final String COLUMN_PASSWORD_USERS = "password";

    //Tabla de clientes
    private static final String TABLE_CLIENTS = "clients";
    private static final String COLUMN_ID_CLIENTS = "id";
    private static final String COLUMN_NAME_CLIENTS = "name";
    private static final String COLUMN_FIRST_NAME_CLIENTS = "first_name";
    private static final String COLUMN_SECOND_NAME_CLIENTS = "second_name";
    private static final String COLUMN_EMAIL_CLIENTS = "email";
    private static final String COLUMN_PHONE_CLIENTS = "phone";

    //Tabla de libros
    private static final String TABLE_BOOKS = "books";
    private static final String COLUMN_ID_BOOKS = "id";
    private static final String COLUMN_NAME_BOOKS = "name";
    private static final String COLUMN_AUTOR_BOOKS = "autor";
    private static final String COLUMN_CATEGORY_BOOKS = "category";


    public BDHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Tabla de usuarios
        String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("+
                COLUMN_ID_USERS + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NAME_USERS + " TEXT, "+
                COLUMN_EMAIL_USERS + " TEXT, "+
                COLUMN_PASSWORD_USERS + " TEXT "+
                ")";

        //Tabla de clientes
        String CREATE_TABLE_CLIENTS = "CREATE TABLE " + TABLE_CLIENTS + "("+
                COLUMN_ID_CLIENTS + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NAME_CLIENTS + " TEXT, "+
                COLUMN_FIRST_NAME_CLIENTS + " TEXT, "+
                COLUMN_SECOND_NAME_CLIENTS + " TEXT, "+
                COLUMN_EMAIL_CLIENTS + " TEXT, "+
                COLUMN_PHONE_CLIENTS + " TEXT "+
                ")";

        //Tabla de libros
        String CREATE_TABLE_BOOKS = "CREATE TABLE " + TABLE_BOOKS + "("+
                COLUMN_ID_BOOKS + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NAME_BOOKS + " TEXT, "+
                COLUMN_AUTOR_BOOKS + " TEXT, "+
                COLUMN_CATEGORY_BOOKS + " TEXT "+
                ")";

        //Ejecutamos la creación de las tablas
        sqLiteDatabase.execSQL(CREATE_TABLE_USERS);
        sqLiteDatabase.execSQL(CREATE_TABLE_CLIENTS);
        sqLiteDatabase.execSQL(CREATE_TABLE_BOOKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //Borramos las tablas de la base de datos si ya existen
        String DROP_TABLE_USERS = "DROP TABLE IF EXISTS " + TABLE_USERS;
        sqLiteDatabase.execSQL(DROP_TABLE_USERS);

        String DROP_TABLE_CLIENTS = "DROP TABLE IF EXISTS " + TABLE_CLIENTS;
        sqLiteDatabase.execSQL(DROP_TABLE_CLIENTS);

        String DROP_TABLE_BOOKS = "DROP TABLE IF EXISTS " + TABLE_BOOKS;
        sqLiteDatabase.execSQL(DROP_TABLE_BOOKS);

        onCreate(sqLiteDatabase);
    }

    /**
     * Agregamos un nuevo usuario
     * @param user Objeto de tipo User
     * @return Inserción de un usuario a la base de datos Library a la tabla Users
     */
    public long addUser(User user){
        //Creamos el contenedor de valores.
        ContentValues values = new ContentValues();

        //Agregamos cada dato requerido a las columnas de la tabla Users
        values.put(COLUMN_NAME_USERS, user.getUser_name());
        values.put(COLUMN_EMAIL_USERS, user.getEmail());
        values.put(COLUMN_PASSWORD_USERS,user.getPassword());

        //Obtenemos el objeto de la base de datos
        SQLiteDatabase database = this.getWritableDatabase();

        //Insertamos el registro en la tabla
        long result = database.insert(TABLE_USERS,null,values);

        //Cerramos la base de datos
        database.close();

        return result;
    }

    /**
     * Agregamos un nuevo usuario
     * @param clients Objeto de tipo Clients
     * @return Inserción de un usuario a la base de datos Library a la tabla Clients
     */
    public long addClients(Clients clients){
        //Creamos el contenedor de valores.
        ContentValues values = new ContentValues();

        //Agregamos cada dato requerido a las columnas de la tabla Users
        values.put(COLUMN_NAME_CLIENTS, clients.getName());
        values.put(COLUMN_FIRST_NAME_CLIENTS, clients.getFirstName());
        values.put(COLUMN_SECOND_NAME_CLIENTS, clients.getSecondName());
        values.put(COLUMN_EMAIL_CLIENTS, clients.getEmail());
        values.put(COLUMN_PHONE_CLIENTS,clients.getPhone());

        //Obtenemos el objeto de la base de datos
        SQLiteDatabase database = this.getWritableDatabase();

        //Insertamos el registro en la tabla
        long result = database.insert(TABLE_CLIENTS,null,values);

        //Cerramos la base de datos
        database.close();

        return result;
    }

    /**
     * Agregamos un nuevo libro
     * @param book Objeto de tipo Book
     * @return Inserción de un usuario a la base de datos Library a la tabla Books
     */
    public long addBooks(Book book) {
        //Creamos el contenedor de valores.
        ContentValues values = new ContentValues();

        //Agregamos cada dato requerido a las columnas de la tabla Users
        values.put(COLUMN_NAME_BOOKS, book.getName());
        values.put(COLUMN_AUTOR_BOOKS, book.getAutor());
        values.put(COLUMN_CATEGORY_BOOKS, book.getCategory());

        //Obtenemos el objeto de la base de datos
        SQLiteDatabase database = this.getWritableDatabase();

        //Insertamos el registro en la tabla
        long result = database.insert(TABLE_BOOKS, null, values);

        //Cerramos la base de datos
        database.close();

        return result;
    }

    /**
     * Conceder acceso a un usuario a la aplicación
     */
    public boolean getAccess(String user, String password){

        boolean result = false;

        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_NAME_USERS + " = " + "\"" + user + "\"" +" AND "+
                COLUMN_PASSWORD_USERS+ " = " + "\""+ password + "\"";

        // Obtenemos el objeto de la base de datos.
        SQLiteDatabase database = this.getWritableDatabase();
        // Realizamos la búsqueda.
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()){
            result = true;
            cursor.close();
        }else{
            result = false;
        }

        database.close();

        return result;
    }

    /**
     * Muestra todos los registros de la tabla de libros
     * @return Lista de libros
     */
    public ArrayList<Book> showBooks(){
        SQLiteDatabase database = this.getWritableDatabase();
        ArrayList<Book> listOfBooks = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_BOOKS;
        // Realizamos la búsqueda.
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Book book = new Book();
                book.setId(cursor.getInt(0));
                book.setName(cursor.getString(1));
                book.setAutor(cursor.getString(2));
                book.setCategory(cursor.getString(3));
                listOfBooks.add(book);
            }while (cursor.moveToNext());
        }

        //Cerramos el cursor
        cursor.close();

        //Regresamos la lista de libros
        return listOfBooks;
    }

    public ArrayList<Clients> showClients(){
        SQLiteDatabase database = this.getWritableDatabase();
        ArrayList<Clients> listOfClients = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_CLIENTS;
        // Realizamos la búsqueda.
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Clients clients = new Clients();
                clients.setId(cursor.getInt(0));
                clients.setName(cursor.getString(1));
                clients.setFirstName(cursor.getString(2));
                clients.setSecondName(cursor.getString(3));
                clients.setEmail(cursor.getString(4));
                listOfClients.add(clients);
            }while (cursor.moveToNext());
        }

        //Cerramos el cursor
        cursor.close();

        //Regresamos la lista de libros
        return listOfClients;
    }

    public Book showInformationBook(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_BOOKS + " WHERE id = "+id+" LIMIT 1";
        // Realizamos la búsqueda.
        Cursor cursor = database.rawQuery(query, null);

        Book book = null;

        if(cursor.moveToFirst()){
            book = new Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setAutor(cursor.getString(2));
            book.setCategory(cursor.getString(3));
        }

        //Cerramos el cursor
        cursor.close();

        //Regresamos la lista de libros
        return book;
    }

    public Clients showInformationClients(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_CLIENTS + " WHERE id = "+id+" LIMIT 1";
        // Realizamos la búsqueda.
        Cursor cursor = database.rawQuery(query, null);

        Clients clients = null;

        if(cursor.moveToFirst()){
            clients = new Clients();
            clients.setId(cursor.getInt(0));
            clients.setName(cursor.getString(1));
            clients.setFirstName(cursor.getString(2));
            clients.setSecondName(cursor.getString(3));
            clients.setEmail(cursor.getString(4));
            clients.setPhone(cursor.getString(5));
        }

        //Cerramos el cursor
        cursor.close();

        //Regresamos la lista de clientes
        return clients;
    }

    public boolean editBook(int id, String name, String autor, String category) {
        boolean isEdited = false;

        //Obtenemos el objeto de la base de datos
        SQLiteDatabase database = this.getWritableDatabase();

        //Actualizamos un registro
        try{
            database.execSQL("UPDATE " + TABLE_BOOKS + " SET name = '" + name + "', autor = '" + autor + "', category = '" + category + "' WHERE id='" + id + "' ");
            isEdited = true;
        }catch (Exception e){
            System.out.println("Error de tipo: "+e);
            isEdited = false;
        }finally {
            //Cerramos la base de datos
            database.close();
        }

        return isEdited;
    }

    public boolean editClients(int id, String name, String firstName, String secondName, String email, String phone) {
        boolean isEdited = false;

        //Obtenemos el objeto de la base de datos
        SQLiteDatabase database = this.getWritableDatabase();

        //Actualizamos un registro
        try{
            database.execSQL("UPDATE " + TABLE_CLIENTS + " SET name = '" + name + "', first_name = '" + firstName + "', second_name = '" + secondName + "', email = '" + email + "', phone = '" + phone +"' WHERE id='" + id + "' ");
            isEdited = true;
        }catch (Exception e){
            System.out.println("Error de tipo: "+e);
            isEdited = false;
        }finally {
            //Cerramos la base de datos
            database.close();
        }

        return isEdited;
    }

    public boolean deleteBook(int id) {
        boolean isDeleted = false;

        //Obtenemos el objeto de la base de datos
        SQLiteDatabase database = this.getWritableDatabase();

        //Actualizamos un registro
        try{
            database.execSQL("DELETE FROM " + TABLE_BOOKS + " WHERE id = '" + id + "'");
            isDeleted = true;
        }catch (Exception e){
            System.out.println("Error de tipo: "+e);
            isDeleted = false;
        }finally {
            //Cerramos la base de datos
            database.close();
        }

        return isDeleted;
    }

    public boolean deleteClients(int id) {
        boolean isDeleted = false;

        //Obtenemos el objeto de la base de datos
        SQLiteDatabase database = this.getWritableDatabase();

        //Actualizamos un registro
        try{
            database.execSQL("DELETE FROM " + TABLE_CLIENTS + " WHERE id = '" + id + "'");
            isDeleted = true;
        }catch (Exception e){
            System.out.println("Error de tipo: "+e);
            isDeleted = false;
        }finally {
            //Cerramos la base de datos
            database.close();
        }

        return isDeleted;
    }
}
