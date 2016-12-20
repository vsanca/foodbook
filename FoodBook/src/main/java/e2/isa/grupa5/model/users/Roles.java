package e2.isa.grupa5.model.users;

public enum Roles {
GUEST, CHEF, WAITER, BARTENDER, MANAGER, SYS_MANAGER, BIDDER
}

//stavio bih da ima samo pet uloga kako je i inicijalno trebalo,
//dakle da umesto chef, waiter, bartender bude WORKER, a onda da se radi
//dublja klasifikacija (jer je u ovom kontekstu bitno da li je radnik ili neka druga vrsta korisnika