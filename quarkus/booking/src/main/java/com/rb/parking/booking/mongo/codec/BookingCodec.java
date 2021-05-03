package com.rb.parking.booking.mongo.codec;

import java.util.Arrays;
import java.util.List;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.rb.parking.booking.mongo.entity.Booking;

public class BookingCodec implements CollectibleCodec<Booking>{

	private final Codec<Document> documentCodec;

	public BookingCodec() {		
		this.documentCodec = 
				MongoClientSettings.getDefaultCodecRegistry()
				.get(Document.class);
	}

	@Override
	public void encode(BsonWriter writer, Booking value, 
			EncoderContext encoderContext) {
		
		Document doc = new Document();
		/**
		 * While encoding id is must.
		 */
		doc.append("_id", new ObjectId(value.getId())); 
		doc.put("date", value.getDate());
		doc.put("date", value.getDate());
		doc.put("hour", value.getHour()); 
		
		String[] bookingsArry = value.getBookings();
		List<String> bookingslst = Arrays.asList(bookingsArry);
		doc.put("bookings", bookingslst);
		
		documentCodec.encode(writer, doc, encoderContext);
	}

	@Override
	public Class<Booking> getEncoderClass() {
		return Booking.class;
	}

	@Override
	public Booking generateIdIfAbsentFromDocument(Booking document) {
		
		if (!documentHasId(document)) {
			/**
			 * Do not use as it creates issues with AWS mongoDB Atlas
			 * document.setId(UUID.randomUUID().toString());
			 * 
			 * use ObjectId.get().toHexString()
			 * or new ObjectId().toHexString()
			 */
			document.setId( ObjectId.get().toHexString());
		}
		
		return document;
	}

	@Override
	public boolean documentHasId(Booking document) {
		return document.getId() != null;
	}

	@Override
	public BsonValue getDocumentId(Booking document) {
		return new BsonString(document.getId());
	}

	@Override
	public Booking decode(BsonReader reader, DecoderContext decoderContext) {
		
		Document document = documentCodec.decode(reader, decoderContext);
		
		Booking booking = new Booking();

		Object obj = new String("_id");
		ObjectId objectId =   document.getObjectId(obj);
		if (objectId != null) {
			booking.setId(objectId.toString());
		}

		booking.setDate(document.getString("date"));
		booking.setHour(document.getString("hour"));

		List<String> list = document.getList("bookings", String.class);
		Object[] objectArray = (list!=null && list.size()>0) 
				? list.toArray() 
				: new String[1];
		String[] stringArray = Arrays.copyOf(objectArray, objectArray.length, String[].class);
		
		booking.setBookings(stringArray);
		return booking;
	}
}
