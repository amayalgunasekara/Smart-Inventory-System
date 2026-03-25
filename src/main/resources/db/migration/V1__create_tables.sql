CREATE TABLE admin (
                       admin_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       role VARCHAR(20) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       last_login TIMESTAMP
);

CREATE TABLE users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       phone VARCHAR(20),
                       address VARCHAR(255),
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(20) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE supplier (
                          supplier_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
                          phone VARCHAR(20),
                          address VARCHAR(255)
);

CREATE TABLE product (
                         product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         category VARCHAR(50),
                         price DOUBLE NOT NULL,
                         stock_quantity INT NOT NULL,
                         reorder_level INT NOT NULL,
                         supplier_id BIGINT,
                         FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id)
);

CREATE TABLE orders (
                        order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_id BIGINT,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        status VARCHAR(20) NOT NULL,
                        total_amount DOUBLE,
                        FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE order_item (
                            order_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            order_id BIGINT,
                            product_id BIGINT,
                            quantity INT NOT NULL,
                            price DOUBLE,
                            FOREIGN KEY (order_id) REFERENCES orders(order_id),
                            FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE notification (
                              notification_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              type VARCHAR(30) NOT NULL,
                              message VARCHAR(500) NOT NULL,
                              sent_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              recipient VARCHAR(100),
                              status VARCHAR(20) NOT NULL,
                              order_id BIGINT,
                              FOREIGN KEY (order_id) REFERENCES orders(order_id)
);