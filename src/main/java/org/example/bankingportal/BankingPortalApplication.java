package org.example.bankingportal;

import lombok.AllArgsConstructor;
import lombok.Setter;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.example.bankingportal.Util.ConcurrentHashMap;
import org.example.bankingportal.configuration.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.bankingportal.repositories")
@EnableMethodSecurity
@EnableConfigurationProperties(value = ApplicationProperties.class)
public class BankingPortalApplication {


    public static void main(String[] args) {
        SpringApplication.run(BankingPortalApplication.class, args);

    }

    @ThreadSafe
    public static class MonitorVehicleTracker {
        @GuardedBy("this")
        private  Map<String, MutablePoint> locations;

        public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
            this.locations = this.deepCopy(locations);
        }



        public synchronized MutablePoint getLocation(String vehicleId) {
            MutablePoint newLocationInMap = this.locations.get(vehicleId);
            return newLocationInMap == null ? null : new MutablePoint(newLocationInMap.x, newLocationInMap.y);
        }

        public synchronized Map<String, MutablePoint> getLocations() {
            return deepCopy(locations);
        }

        private synchronized Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> locations) {
            Map<String, MutablePoint> copy = new HashMap<>();
            for (String mutableClazz : locations.keySet()) {
                copy.put(mutableClazz, locations.get(mutableClazz));

            }
            return Collections.unmodifiableMap(copy);
        }
        public synchronized void setLocations(String id, int x, int y) {
            MutablePoint newLocation = this.locations.get(id);
            if(newLocation == null){
                throw new IllegalArgumentException("No such vehicle id: " + id);
            }



        }
    }

    @ThreadSafe
    public static class DelegatingMonitorVehicle {
        private final ConcurrentMap<String, MutablePoint> locations;
        private final Map<String, MutablePoint> vehicles;


        public DelegatingMonitorVehicle(ConcurrentHashMap<String, MutablePoint> locations, Map<String, MutablePoint> vehicles) {
            this.locations = new java.util.concurrent.ConcurrentHashMap<>();
            this.vehicles = Collections.unmodifiableMap(vehicles);
        }

        public synchronized MutablePoint getLocation(String vehicleId) {
            MutablePoint newLocation = this.locations.get(vehicleId);
            return newLocation == null ? null : new MutablePoint(newLocation.x, newLocation.y);
        }
        public synchronized Map<String, MutablePoint> getLocations() {
            return locations;
        }

        public void setLocation(String id, int x, int y) {
            if (!locations.containsKey(id))
                throw new IllegalArgumentException(
                        "invalid vehicle name: " + id);
            MutablePoint l = locations.get(id);
            for(Map.Entry<String, MutablePoint> entry : vehicles.entrySet()){
                entry.setValue(new MutablePoint(l.x, l.y));
            }
        }

        public synchronized void setLocations(String id, int x, int y) {
            if(locations.replace(id, new MutablePoint(x, y)) != null){
                throw new IllegalArgumentException("Duplicate vehicle id: " + id);

            }
//            this.locations.put(id, new MutablePoint(x, y));
            MutablePoint newLocation = this.locations.get(id);
            if(newLocation == null){
                throw new IllegalArgumentException("No such vehicle id: " + id);

            }
            new MutablePoint(newLocation.x, newLocation.y);
        }
    }


    @AllArgsConstructor
    public static class MutablePoint {

        private int x;
        private int y;
        public void set(int x, int y) {
            this.x = x;
            this.y = y;

        }

        private static class Builder{
            private int x;
            private int y;
            private final Set<String> elements = new HashSet<>();

            public Builder x(int x) {
                this.x = x;
                return this;
            }
            public Builder y(int y) {
                this.y = y;
                return this;
            }
            public MutablePoint build() {
                return new MutablePoint(this.x, this.y);
            }
            public Builder forEach(Consumer<MutablePoint> c) {
                c.accept(this.build());
                return this;
            }

        }


    }

    public static class HiddenClass{
        Set<Integer> newSets = new HashSet<>();

        public void setValue(int value) {
            newSets.add(value);
        }

        public void remove(int value) {
            this.newSets.remove(value);
        }
        public void addNewRandomValues(){
            Random r = new Random();
            for(int i = 0; i < 10; i++){
                this.setValue(r.nextInt());
            }
        }
    }


}



