package mk.ukim.finki.emt.lab1.model.exceptions;

public class BookWasTakenException extends RuntimeException {
        public BookWasTakenException(Long id) {
            super(String.format("Book with id: %d was taken.", id));
        }
    }
