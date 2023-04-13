-- some test users

INSERT INTO users_roles (id, user_role_enum)
values (1, 'ADMIN'),
       (2, 'USER');

INSERT INTO users (id, email, first_name, last_name, phone, image_url, is_active, want_deletion, password)
VALUES (1, 'admin@example.com', 'Admin', 'Adminov', '0986 20 65 30', true, false , false ,
        '57e7759fd2d59275fc3c3cd5dd2ace5013b39ee972999412f3f5f5c3382b6765c2571ef86648abe2'),
-- pass is: topsecret
       (2, 'user@example.com', 'User', 'Userov', '0236 25 84 21', true , false , false ,
        '57e7759fd2d59275fc3c3cd5dd2ace5013b39ee972999412f3f5f5c3382b6765c2571ef86648abe2');


INSERT INTO users_user_roles (user_entity_id, user_roles_id)
VALUES (1, 1),
       (2, 2);


INSERT INTO rooms (id, room_type, square_metres)
VALUES (1, 'BEDROOM', 20),
       (2, 'KITCHEN', 15),
       (3, 'LIVING_ROOM', 35),
       (4, 'BATHROOM', 10),
       (6, 'ENTRANCE_HALL', 35),
       (5, 'OTHER', 40);

-- INSERT INTO designs (id, date, description, title, price, style, creator_id, room_id, image_url)
-- VALUES ('5ebdd23e-7bf3-4166-ab67-98242b871f6a', '2020-09-25', 'Simple update', 'Fenix', 3200, 'BOHEMIAN', 1, 1,
--         'https://evolveindia.co/wp-content/uploads/2021/07/8_Achieve-An-Aesthetic-Look-With-Neutral-Accents-Modern-Bedroom-Interior-Design.jpg'),
--        ('5ebdd23e-7bf3-4166-ab67-98242b871f6b', '2021-08-24', 'New floor design', 'FloorColour', 500, 'BOHEMIAN', 2, 3,
--         'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/modern-kitchen-meredith-mcbrearty-dallas-1638547900.jpg'),
--        ('5ebdd23e-7bf3-4166-ab67-98242b871f6c', '2021-05-14', 'Effective kitchen design is the process of combining layout, surfaces, appliances and design details to form a cooking space that''s easy to use and fun to cook and socialise in. The world of effective kitchen design is a maze of design ideas, guides and tips – and a seemingly endless stream of expert advice. Link: https://www.abv.bg/', 'KitchenX', 2500, 'MODERN', 1, 2,
--         'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/modern-kitchen-meredith-mcbrearty-dallas-1638547900.jpg'),
--        ('5ebdd23e-7bf3-4166-ab67-98242b871f6d', '2020-06-12', 'Office splash', 'Splash', 5000, 'COASTAL', 2, 6,
--         'https://evolveindia.co/wp-content/uploads/2021/07/8_Achieve-An-Aesthetic-Look-With-Neutral-Accents-Modern-Bedroom-Interior-Design.jpg'),
--        ('5ebdd23e-7bf3-4166-ab67-98242b871f6e', '2020-09-25', 'Bath update', 'Fenix', 5000, 'TRADITIONAL', 1, 4,
--         'https://evolveindia.co/wp-content/uploads/2021/07/8_Achieve-An-Aesthetic-Look-With-Neutral-Accents-Modern-Bedroom-Interior-Design.jpg'),
--        ('5ebdd23e-7bf3-4166-ab67-98242b871f6f', '2021-08-24', 'New floor design', 'Entrance fix', 850, 'MODERN', 2, 6,
--         'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/modern-kitchen-meredith-mcbrearty-dallas-1638547900.jpg');

INSERT INTO designs (id, date, description, title, price, style, creator_id, room_id)
VALUES ('5ebdd23e-7bf3-4166-ab67-98242b871f6a', '2020-09-25', 'Simple update', 'Fenix', 3200, 'BOHEMIAN', 1, 1),
       ('5ebdd23e-7bf3-4166-ab67-98242b871f6b', '2021-08-24', 'New floor design - Earthy hues paired with neutrals or warm colours combined with cool colours make for an interesting boho colour palette. Yellow, browns, white, green, blue, grey, and red are some of the most popular bohemian colours',
        'FloorColour', 500, 'BOHEMIAN', 2, 3);
INSERT INTO pictures (id, image_url, design_id)
VALUES ( 1,
        'https://evolveindia.co/wp-content/uploads/2021/07/8_Achieve-An-Aesthetic-Look-With-Neutral-Accents-Modern-Bedroom-Interior-Design.jpg',
         '5ebdd23e-7bf3-4166-ab67-98242b871f6a'),
       ( 2,
        'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/modern-kitchen-meredith-mcbrearty-dallas-1638547900.jpg',
         '5ebdd23e-7bf3-4166-ab67-98242b871f6b'),
       ( 3,
         'https://www.decorilla.com/online-decorating/wp-content/uploads/2021/08/Cozy-Bohemian-interior-design-living-room-Article.jpg',
         '5ebdd23e-7bf3-4166-ab67-98242b871f6a'),
       ( 4,
         'http://cdn.home-designing.com/wp-content/uploads/2022/07/white-boho-living-room.jpg',
         '5ebdd23e-7bf3-4166-ab67-98242b871f6a');
