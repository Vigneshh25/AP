//package transactionmanagement;
//
//import spark.Spark;
//import com.google.gson.Gson;
//
//public class Main {
//    public static void main(String[] args) {
//        TransactionRepository transactionRepository = new TransactionRepository();
//        TransactionLogger transactionLogger = new TransactionLogger();
//        TransactionProcessor transactionProcessor = new TransactionProcessor(transactionRepository, transactionLogger);
//        TransactionManager transactionManager = new TransactionManager(transactionRepository, transactionLogger, transactionProcessor);
//        Gson gson = new Gson();
//
//        Spark.post("/transactions", (request, response) -> {
//            TransactionRequest transactionRequest = gson.fromJson(request.body(), TransactionRequest.class);
//            String transactionId = transactionManager.createTransaction(transactionRequest.getAmount(), transactionRequest.getDescription());
//            response.status(201);
//            return gson.toJson(new MessageResponse("Transaction created successfully", transactionId));
//        });
//
//        Spark.get("/transactions/:transactionId", (request, response) -> {
//            String transactionId = request.params(":transactionId");
//            Transaction transaction = transactionManager.getTransaction(transactionId);
//            if (transaction == null) {
//                response.status(404);
//                return gson.toJson(new MessageResponse("Transaction not found"));
//            }
//            return gson.toJson(transaction);
//        });
//
//        Spark.get("/transactions", (request, response) -> {
//            return gson.toJson(transactionManager.getAllTransactions());
//        });
//
//        Spark.post("/transactions/process", (request, response) -> {
//            transactionManager.processTransactions();
//            return gson.toJson(new MessageResponse("Transactions processed"));
//        });
//
//        Spark.awaitInitialization();
//    }
//}
//
