package com.tnahsin.bookMovies.dtos;


import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    @NonNull
    private ObjectId showId;
    @NonNull
    private ObjectId userId;
    @NonNull
    private List<String> seatIds;
}
