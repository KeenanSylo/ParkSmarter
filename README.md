# ParkSmarter

A parking garage management system that handles vehicle parking, ticketing, and pricing. Built with Java and Gradle, featuring comprehensive unit tests with JaCoCo coverage.

## the features

- **Vehicle Management** - Register vehicles with license plate validation
- **Spot Allocation** - Automatically assign available parking spots
- **Ticket System** - Generate timestamped tickets on entry
- **Dynamic Pricing** - Calculate parking fees based on duration and configurable hourly rates
- **State Tracking** - Real-time occupancy status of all parking spots

## How It Works

### Parking a car
1. User provides vehicle details (license plate, brand, model, color)
2. License plate is validated against format rules
3. First available parking spot is assigned
4. Entry ticket is created with timestamp
5. Spot marked as occupied

### Exiting a car
1. User provides parking spot ID
2. System calculates duration between entry and exit times
3. Price is computed based on duration (rounded up to nearest hour, minimum 1 hour)
4. Spot is vacated and made available for next vehicle
5. User is charged the calculated fee

## Setup & Running

### Build the project
```bash
./gradlew build
```

### Run the application
```bash
./gradlew run --console=plain
```

### Run tests with coverage report
```bash
./gradlew test jacocoTestReport
```
Coverage report will be available at `app/build/jacocoHtml/index.html`

## Configuration

Default parking rate is **$20.00 per hour**. Customize by passing a rate to `StandardRateRepository`:
```java
RateRepository customRate = new StandardRateRepository(25.0); // $25/hour
```

## Testing

Comprehensive unit tests cover:
- Vehicle validation and creation
- Parking spot occupancy management
- Ticket generation and pricing calculations
- Edge cases (negative rates, invalid times, full garage, etc.)

Test execution and coverage reporting configured via JaCoCo.