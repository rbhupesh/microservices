package com.rb.parking.booking.mongo.codec;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

import com.rb.parking.booking.mongo.entity.Booking;

public class BookingCodecProvider implements CodecProvider {

	@SuppressWarnings("unchecked")
	@Override
	public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
		if (clazz == Booking.class) {
            return (Codec<T>) new BookingCodec();
        }
        return null;
	}

}
