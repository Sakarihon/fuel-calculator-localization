CREATE DATABASE IF NOT EXISTS fuel_db
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE fuel_db;

CREATE TABLE IF NOT EXISTS localization_strings (
                                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                                    `key` VARCHAR(100) NOT NULL,
    value VARCHAR(255) NOT NULL,
    language VARCHAR(10) NOT NULL,
    UNIQUE KEY unique_key_lang (`key`, `language`)
    );

CREATE TABLE IF NOT EXISTS calculation_records (
                                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                                   distance DOUBLE NOT NULL,
                                                   consumption DOUBLE NOT NULL,
                                                   price DOUBLE NOT NULL,
                                                   total_fuel DOUBLE NOT NULL,
                                                   total_cost DOUBLE NOT NULL,
                                                   language VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- EN
INSERT INTO localization_strings (`key`, value, language) VALUES
                                                              ('distance.label', 'Distance (km)', 'en'),
                                                              ('consumption.label', 'Fuel Consumption', 'en'),
                                                              ('price.label', 'Fuel Price', 'en'),
                                                              ('calculate.button', 'Calculate', 'en'),
                                                              ('result.label', 'Fuel: {0} L | Cost: {1}', 'en'),
                                                              ('invalid.input', 'Invalid input', 'en'),

-- FR
                                                              ('distance.label', 'Distance (km)', 'fr'),
                                                              ('consumption.label', 'Consommation (L/100 km)', 'fr'),
                                                              ('price.label', 'Prix du carburant', 'fr'),
                                                              ('calculate.button', 'Calculer', 'fr'),
                                                              ('result.label', 'Carburant: {0} L | Coût: {1}', 'fr'),
                                                              ('invalid.input', 'Entrée invalide', 'fr'),

-- JA
                                                              ('distance.label', '距離 (km)', 'ja'),
                                                              ('consumption.label', '燃費 (L/100km)', 'ja'),
                                                              ('price.label', '燃料価格', 'ja'),
                                                              ('calculate.button', '計算', 'ja'),
                                                              ('result.label', '燃料: {0} L | コスト: {1}', 'ja'),
                                                              ('invalid.input', '無効な入力', 'ja'),

-- FA
                                                              ('distance.label', 'مسافت (کیلومتر)', 'fa'),
                                                              ('consumption.label', 'مصرف سوخت', 'fa'),
                                                              ('price.label', 'قیمت سوخت', 'fa'),
                                                              ('calculate.button', 'محاسبه', 'fa'),
                                                              ('result.label', 'سوخت: {0} لیتر | هزینه: {1}', 'fa'),
                                                              ('invalid.input', 'ورودی نامعتبر', 'fa');