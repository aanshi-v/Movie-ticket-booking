### What It Does:
It fetches paginated booking records for a given userId from the database.

Converts each Booking entity into a BookingHistoryResponse DTO.

If no bookings are found, it throws a ResourceNotFoundException.
