package com.example.bookingmanagementsystem.business;

import com.example.bookingmanagementsystem.data.*;
import com.example.bookingmanagementsystem.dto.RoomReservationDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestId());
        });
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id : roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }
        //            @Override
//            public int compare(RoomReservation o1, RoomReservation o2) {
// sort by roomName, roomNumber
//                if (o1.getRoomName().equals(o2.getRoomName())) {
//                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
//                }
//                return o1.getRoomName().compareTo(o2.getRoomName());
//            }
        roomReservations.sort((o1, o2) -> {
            // sort by roomId,roomNumber
            if (Objects.equals(o1.getRoomId(), o2.getRoomId())) {
                return o1.getRoomNumber().compareTo(o2.getRoomNumber());
            }
            return (int) (o1.getRoomId() - o2.getRoomId());
        });
        return roomReservations;
    }
    public List<RoomReservation> getBookedReservation() {
        ArrayList<RoomReservation> bookedRoomReservation = new ArrayList<>();
        List<Reservation> reservations = reservationRepository.findAll();
        reservations.forEach(reservation -> {
            Optional<Guest> guest = guestRepository.findById(reservation.getGuestId());
            Optional<Room> room = roomRepository.findById(reservation.getRoomId());
            Date date = reservation.getReservationDate();
            if(guest.isPresent() && room.isPresent()) {
                bookedRoomReservation.add(createRoomReservation(
                        reservation.getReservationId(),guest.get(),room.get(),date));
            }
        });

        return bookedRoomReservation;
    }
    public List<Guest> getGuests() {
        List<Guest> guests = guestRepository.findAll();
        guests.sort((o1, o2) -> {
            if (o1.getLastName().equals(o2.getLastName())) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
            return o1.getLastName().compareTo(o2.getLastName());
        });
        return guests;
    }

    public List<Room> getRooms() {
        List<Room> rooms = (List<Room>) roomRepository.findAll();
        rooms.sort(new Comparator<>() {
            @Override
            public int compare(Room o1, Room o2) {
                // sort by RoomName, RoomNumber
                if (o1.getName().equals(o2.getName())) {
                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
                }
                return o1.getName().compareTo(o2.getName());

            }
        });
        return rooms;
    }

    public void addGuest(Guest guest) {
        if (guest == null) {
            throw new RuntimeException("guest_cannot_be_null");
        }
        guestRepository.save(guest);

    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    private RoomReservation createRoomReservation(long id, Guest guest, Room room, Date date) {
        return new RoomReservation(id,room.getId(),guest.getGuestId(), room.getName(),
                room.getRoomNumber(), guest.getFirstName(),
                guest.getLastName(), date);
    }

    public RoomReservation createRoomReservation(Reservation reservation){
        Optional<Guest> guestOptional = guestRepository.findById(reservation.getGuestId());
        Optional<Room> roomOptional = roomRepository.findById(reservation.getRoomId());
        Date date = reservation.getReservationDate();

        Guest guest = guestOptional.get();
        Room room = roomOptional.get();
        long id = reservation.getReservationId();
        if(guest != null && room != null){

            return new RoomReservation(id,room.getId(),guest.getGuestId(), room.getName(),
                    room.getRoomNumber(), guest.getFirstName(),
                    guest.getLastName(), date);
        }
        return new RoomReservation();
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    public RoomReservationDTO createRoomReservationDTO(RoomReservation roomRe) {
        return new RoomReservationDTO(roomRe.getId(), roomRe.getRoomId(),roomRe.getGuestId(),
                roomRe.getRoomName(), roomRe.getRoomNumber(),roomRe.getFirstName(),roomRe.getLastName(),
                roomRe.getDateString());
    }

    public void deleteById(Integer id) {
        reservationRepository.deleteById(Long.valueOf(id));
    }
}

