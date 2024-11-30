//package org.example.bankingportal.aop;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AdviceName;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.example.bankingportal.entities.User;
//import org.example.bankingportal.payload.UserRegistrationRequest;
//import org.example.bankingportal.service.UserService;
//import org.example.bankingportal.service.UserServiceImpl;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.Arrays;
//
//import static com.nimbusds.jose.shaded.gson.internal.$Gson$Types.getRawType;
//
//@Slf4j
//@Aspect
//public class Logging {
//
//    @AdviceName(value = "take care of all logging concerns in this method!")
//    @Around(value = "@annotation(ToLog)")
//    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
//        String methodName = joinPoint.getSignature().getName();
//        Object[] args = joinPoint.getArgs();
//
//        log.info("Method {}takes {} as arguments", methodName, Arrays.asList(args));
//
//
//        UserService userService = new UserServiceImpl();
//        UserRegistrationRequest request = new UserRegistrationRequest();
//        User user = userService.createUser(request);
//
//        Object[] returnedArgs = {user};
//
//        Object proceed = joinPoint.proceed(returnedArgs);
//        log.info("Method executed and returned{}", proceed);
//
//        return proceed;
//
//
//    }
////
////    public static void main(String[] args){
////
////        var c = new AnnotationConfigApplicationContext(Logging.class);
////        var context = c.getBean(UserServiceImpl.class);
////        UserRegistrationRequest request = UserRegistrationRequest.builder()
////                .name("john")
////                .email("john@example.com")
////                .password("password")
////                .countryCode("123")
////                .build();
////       var user =  context.createUser(request);
////       log.info(user.toString());
////    }
//
//    public static Class<?> comparableClassForObjects(Object x) {
//        if (x instanceof Comparable) {
//            Class<?> c;
//            Type[] ts, as;
//            ParameterizedType pt;
//            if ((c = x.getClass()) == String.class) {
//                return c;
//            }
//            if ((ts = c.getGenericInterfaces()) != null) {
//                for (Type t : ts) {
//                    if (t instanceof ParameterizedType &&
//                            ((pt = (ParameterizedType) t)).getRawType().equals(Comparable.class)
//                            && (as = pt.getActualTypeArguments()) != null
//                            && as.length == 1 && as[0] == c) {
//
//                        return c;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//}
