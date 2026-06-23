CREATE TABLE accounts (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          username VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          access_level VARCHAR(255) NOT NULL,
                          account_code VARCHAR(255),
                          account_status ENUM('ACTIVE','INACTIVE') NOT NULL DEFAULT 'ACTIVE'
);

/**/

CREATE TABLE travel_packages (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 travel_package_code VARCHAR(255),
                                 name VARCHAR(255),
                                 member_count INTEGER,
                                 estimated_duration VARCHAR(255),
                                 total_price DECIMAL(8,2),
                                 reservation_admission_percentage FLOAT,
                                 travel_package_status ENUM('ACTIVE','ARCHIVED','INACTIVE')
);

/**/

CREATE TABLE travel_package_details (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        travel_package_detail_code VARCHAR(255),
                                        destination_title VARCHAR(255),
                                        destination_description TEXT,
                                        google_map_url VARCHAR(500),
                                        travel_package_id BIGINT,
                                        FOREIGN KEY (travel_package_id) REFERENCES travel_packages(id)
);