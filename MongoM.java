package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import model.TweetsM;


public class MongoM   {
    private Mongo mongo;
    private  DB db;
    private DBObject textSearchCommand;
    private DBCollection collection;

    public MongoM() {
        this.mongo = new Mongo("localhost", 27017);
        this.textSearchCommand = new BasicDBObject();
        this.db = mongo.getDB("prueba");
        this.collection=db.getCollection("tweets4");
    }
    
 	public List<TweetsM> searchCompa(){
       List<TweetsM> list = new ArrayList<>();
       String searchString = "VTR Claro Movistar WOM";
       DBCursor cursor = this.collection.find(new BasicDBObject("$text", new BasicDBObject("$search", searchString)));
        while (cursor.hasNext()) {
           DBObject document = cursor.next();
           TweetsM  data = new TweetsM();
            data.setText((String) document.get("text"));
            list.add(data);
        }



        return list;     
}

	// public List<TweetsM> searchCompa(){
		// List<TweetsM> list = new ArrayList<>();
        // String searchString = "Movistar";
        // DBObject query = new BasicDBObject("Movistar", new BasicDBObject("$regex", "malo"));
  		// DBCursor cursor= collection.find(query);      
        // while (cursor.hasNext()) {
            // DBObject document = cursor.next();
            // TweetsM  data = new TweetsM();
            // data.setText((String) document.get("text"));
            // list.add(data);
        // }
        // return list;
// }


// public List<TweetsM> searchCompa(String str_date){
//         List<TweetsM> list = new ArrayList<>();
//         String searchString = "VTR Claro Movistar WOM";
//         try {
//             DateFormat formatter;
//             formatter = new SimpleDateFormat("dd-MM-yyyy");
//             Date date = (Date) formatter.parse(str_date);
//             java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
//             BasicDBObject busqueda = new BasicDBObject("timestamp", timeStampDate );
//             busqueda.append("$text", new BasicDBObject("$search", searchString));
//             DBCursor cursor = this.collection.find(busqueda);
//             while (cursor.hasNext()) {
//                 DBObject document = cursor.next();
//                 TweetsM data = new TweetsM();
//                 data.setText((String) document.get("text"));
//                 list.add(data);
//             }
//             return list;  
//         } catch (ParseException e) {
//             return list;
//         }}
    
    public List<TweetsM> searchConcepto(String concepto){
        List<TweetsM> list = new ArrayList<>();
         String searchString = concepto;
        DBCursor cursor = this.collection.find(new BasicDBObject("$text", new BasicDBObject("$search", searchString)));
        while (cursor.hasNext()) {
            DBObject document = cursor.next();
            TweetsM data = new TweetsM();
            data.setText((String) document.get("text"));
            list.add(data);
        }

        return list;   
        }




}

