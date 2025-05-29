/*
 * package com.sanjib.edureka.student;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.kafka.core.KafkaTemplate; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestHeader; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import jakarta.validation.Valid;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/v1") public class Controller {
 * 
 * @Autowired TokenService tokenService;
 * 
 * @Autowired PaymentRepository paymentRepository;
 * 
 * @Autowired KafkaTemplate<String, String> kafkaTemplate;
 * 
 * @PostMapping("/payment/credit/{customerId}") public ResponseEntity<?>
 * creditPayment(@RequestHeader("Authorization") String token,
 * 
 * @RequestHeader("Usertype") String usertype, @PathVariable("customerId") Long
 * customerId,
 * 
 * @Valid @RequestBody Payment paymenView) {
 * 
 * if (tokenService.validateToken(token) &&
 * "customer".equalsIgnoreCase(usertype)) {
 * 
 * Payment paymentInDB = paymentRepository.findPaymentByCustomerId(customerId);
 * 
 * if (paymentInDB == null) {
 * 
 * paymentInDB = new Payment(); paymentInDB.setBalance(paymenView.getBalance());
 * paymentInDB.setCustomerId(customerId);
 * paymentInDB.setPaymentInfo(paymenView.getPaymentInfo());
 * 
 * } else { paymentInDB.setBalance(paymentInDB.getBalance() +
 * paymenView.getBalance()); } paymentRepository.save(paymentInDB); return new
 * ResponseEntity<Payment>(paymentInDB, HttpStatus.OK); } else { return
 * ResponseEntity.status(401).body("Invalid Details"); }
 * 
 * }
 * 
 * @PostMapping("/payment/debit/{orderId}") public ResponseEntity<String>
 * debitPayment(@RequestHeader("Authorization") String token,
 * 
 * @RequestHeader("Usertype") String usertype, @PathVariable("orderId") String
 * orderId,
 * 
 * @RequestBody Payment paymenView) {
 * 
 * if (tokenService.validateToken(token) &&
 * "customer".equalsIgnoreCase(usertype)) {
 * 
 * Payment paymentInDB =
 * paymentRepository.findPaymentByCustomerId(paymenView.getCustomerId());
 * 
 * if (paymentInDB.getBalance() < paymenView.getBalance()) {
 * kafkaTemplate.send("order_cancel", orderId); return
 * ResponseEntity.status(200).body("Insufficient Balance");
 * 
 * } else { paymentInDB.setBalance(paymentInDB.getBalance() -
 * paymenView.getBalance()); paymentRepository.save(paymentInDB); return
 * ResponseEntity.status(200).body("Payment successful"); } } else { return
 * ResponseEntity.status(401).body("Invalid Details"); } } }
 */