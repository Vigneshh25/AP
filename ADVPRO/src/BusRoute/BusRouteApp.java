package BusRoute;

import java.util.*;

class BusStop {
    String name;

    public BusStop(String name) {
        this.name = name;
    }
}

class BusRoute {
    BusStop start;
    BusStop end;
    double distance;

    public BusRoute(BusStop start, BusStop end, double distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }
}

class BusRouteApp {
    List<BusStop> busStops;
    List<BusRoute> busRoutes;

    public BusRouteApp() {
        busStops = new ArrayList<>();
        busRoutes = new ArrayList<>();
    }

    public void addBusStop(String stopName) {
        busStops.add(new BusStop(stopName));
    }

    public void removeBusStop(String stopName) {
        BusStop stopToRemove = findBusStopByName(stopName);
        if (stopToRemove != null) {
            busStops.remove(stopToRemove);
            busRoutes.removeIf(route -> route.start.equals(stopToRemove) || route.end.equals(stopToRemove));
        }
    }

    public void addBusRoute(String startStopName, String endStopName, double distance) {
        BusStop start = findBusStopByName(startStopName);
        BusStop end = findBusStopByName(endStopName);

        if (start != null && end != null) {
            busRoutes.add(new BusRoute(start, end, distance));
        }
    }

    public void updateBusRoute(String startStopName, String endStopName, double newDistance) {
        BusRoute routeToUpdate = findBusRoute(startStopName, endStopName);
        if (routeToUpdate != null) {
            routeToUpdate.distance = newDistance;
        }
    }

    public List<BusStop> findShortestRoute(String startStopName, String endStopName) {
        BusStop start = findBusStopByName(startStopName);
        BusStop end = findBusStopByName(endStopName);

        if (start != null && end != null) {
            Map<BusStop, Double> distances = new HashMap<>();
            Map<BusStop, BusStop> previousStops = new HashMap<>();
            Set<BusStop> unvisitedStops = new HashSet<>(busStops);

            for (BusStop stop : busStops) {
                distances.put(stop, Double.POSITIVE_INFINITY);
            }
            distances.put(start, 0.0);

            while (!unvisitedStops.isEmpty()) {
                BusStop currentStop = getClosestStop(unvisitedStops, distances);

                if (currentStop.equals(end)) {
                    return reconstructShortestRoute(previousStops, end);
                }

                unvisitedStops.remove(currentStop);

                for (BusRoute route : busRoutes) {
                    if (route.start.equals(currentStop) || route.end.equals(currentStop)) {
                        BusStop neighbor = route.start.equals(currentStop) ? route.end : route.start;
                        double distanceToNeighbor = distances.get(currentStop) + route.distance;

                        if (distanceToNeighbor < distances.get(neighbor)) {
                            distances.put(neighbor, distanceToNeighbor);
                            previousStops.put(neighbor, currentStop);
                        }
                    }
                }
            }
        }

        return null; // No route found
    }
    private BusStop getClosestStop(Set<BusStop> unvisitedStops, Map<BusStop, Double> distances) {
        BusStop closestStop = null;
        double minDistance = Double.POSITIVE_INFINITY;

        for (BusStop stop : unvisitedStops) {
            double distance = distances.get(stop);
            if (distance < minDistance) {
                closestStop = stop;
                minDistance = distance;
            }
        }

        return closestStop;
    }

    private List<BusStop> reconstructShortestRoute(Map<BusStop, BusStop> previousStops, BusStop end) {
        List<BusStop> shortestRoute = new ArrayList<>();
        BusStop currentStop = end;

        while (previousStops.containsKey(currentStop)) {
            shortestRoute.add(currentStop);
            currentStop = previousStops.get(currentStop);
        }

        Collections.reverse(shortestRoute);
        return shortestRoute;
    }

    private BusStop findBusStopByName(String stopName) {
        for (BusStop stop : busStops) {
            if (stop.name.equals(stopName)) {
                return stop;
            }
        }
        return null;
    }

    private BusRoute findBusRoute(String startStopName, String endStopName) {
        for (BusRoute route : busRoutes) {
            if ((route.start.name.equals(startStopName) && route.end.name.equals(endStopName)) ||
                    (route.start.name.equals(endStopName) && route.end.name.equals(startStopName))) {
                return route;
            }
        }
        return null;
    }

    public void listAllBusStops() {
        for (BusStop stop : busStops) {
            System.out.println(stop.name);
        }
    }

    public void listAllBusRoutes() {
        for (BusRoute route : busRoutes) {
            System.out.println("Route from " + route.start.name + " to " + route.end.name + ": " + route.distance);
        }
    }

    public double calculateTotalDistance(String startStopName, String endStopName) {
        List<BusStop> stopsInRoute = findShortestRoute(startStopName, endStopName);
        double totalDistance = 0.0;

        if (stopsInRoute != null) {
            for (int i = 0; i < stopsInRoute.size() - 1; i++) {
                BusStop currentStop = stopsInRoute.get(i);
                BusStop nextStop = stopsInRoute.get(i + 1);

                BusRoute route = findBusRoute(currentStop.name, nextStop.name);
                if (route != null) {
                    totalDistance += route.distance;
                }
            }
        }

        return totalDistance;
    }

    public void displayBusStops() {
        System.out.println("Bus Stops:");
        for (BusStop stop : busStops) {
            System.out.println("- " + stop.name);
        }
    }

    public void displayBusRoutes() {
        System.out.println("Bus Routes:");
        for (BusRoute route : busRoutes) {
            System.out.println("From " + route.start.name + " to " + route.end.name + ": " + route.distance + " km");
        }
    }

    public static void main(String[] args) {
        BusRouteApp app = new BusRouteApp();

        app.addBusStop("StopA");
        app.addBusStop("StopB");
        app.addBusStop("StopC");

        app.addBusRoute("StopA", "StopB", 5.0);
        app.addBusRoute("StopB", "StopC", 10.0);

        System.out.println("Bus Stops:");
        app.displayBusStops();

        System.out.println("Bus Routes:");
        app.displayBusRoutes();

        List<BusStop> shortestRoute = app.findShortestRoute("StopC", "StopA");
        System.out.println("Total Distance for Route: " + app.calculateTotalDistance("StopA", "StopC") + " km");
        if (shortestRoute != null) {
            System.out.println("Shortest route:");
            for (BusStop stop : shortestRoute) {
                System.out.println(stop.name);
            }
        } else {
            System.out.println("No route found.");
        }

        app.removeBusStop("StopB");

        System.out.println("Updated bus stops:");
        app.listAllBusStops();

        app.updateBusRoute("StopA", "StopC", 15.0);

        System.out.println("Updated bus routes:");
        app.listAllBusRoutes();
    }
}
