function fn() {
    var env = karate.env; // get java system property 'karate.env'

    var config = {
        env: env
    };

    karate.log('karate.env selected environment was:', env);

    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);

    return config;
}