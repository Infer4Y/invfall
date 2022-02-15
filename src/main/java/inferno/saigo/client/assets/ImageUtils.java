package inferno.saigo.client.assets;

import inferno.saigo.client.utils.display.DisplayReference;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {
    public static void drawScaledImage(Image image, Component canvas, Graphics graphics) {
        int image_width = image.getWidth(null);
        int image_height = image.getHeight(null);

        double image_aspect = (double) image_height / image_width;

        int canvas_width = canvas.getWidth();
        int canvas_height = canvas.getHeight();

        double canvas_aspect = (double) canvas_height / canvas_width;

        int x1 = 0,
            y1 = 0,
            x2,
            y2;     // top left X position, top left Y position, bottom right X position, bottom right Y position

        if (image_width < canvas_width && image_height < canvas_height) {
            // the image is smaller than the canvas
            x1 = (canvas_width - image_width)  / 2;
            y1 = (canvas_height - image_height) / 2;
            x2 = image_width + x1;
            y2 = image_height + y1;

        } else {
            if (canvas_aspect > image_aspect) {
                y1 = canvas_height;
                // keep image aspect ratio
                canvas_height = (int) (canvas_width * image_aspect);
                y1 = (y1 - canvas_height) / 2;
            } else {
                x1 = canvas_width;
                // keep image aspect ratio
                canvas_width = (int) (canvas_height / image_aspect);
                x1 = (x1 - canvas_width) / 2;
            }
            x2 = canvas_width + x1;
            y2 = canvas_height + y1;
        }

        graphics.drawImage(image, x1, y1, x2, y2, 0, 0, image_width, image_height, null);
    }

    public static BufferedImage resize(BufferedImage buffered_image, int target_width, int target_height) {

        double  scale_x = (double) target_width / buffered_image.getWidth(),
                scale_y = (double) target_height / buffered_image.getHeight(),
                scale = Math.min(scale_x, scale_y);

        int image_width = (int) (buffered_image.getWidth() * scale),
            image_height = (int) (buffered_image.getHeight() * scale);

        Image temporary_scaled_instance = buffered_image.getScaledInstance(image_width, image_height, Image.SCALE_DEFAULT);

        //BufferedImage resized_buffered_image = new BufferedImage(image_width, image_height, buffered_image.getType());
        BufferedImage resized_buffered_image = DisplayReference.defaultConfiguration.createCompatibleImage(image_width, image_height, Transparency.TRANSLUCENT);
        resized_buffered_image.setAccelerationPriority(1);

        Graphics2D graphics2D = resized_buffered_image.createGraphics();
        graphics2D.drawImage(temporary_scaled_instance, 0, 0, null);
        graphics2D.dispose();

        temporary_scaled_instance.flush();

        return resized_buffered_image;
    }
}
