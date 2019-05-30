package com.example.demo;

import java.awt.image.BufferedImage;

import org.springframework.stereotype.Service;

@Service
public class ThumbnailService {
	
	public BufferedImage makeThumbnail(BufferedImage sourceImage) {
		int biggerDimensionMax = 280;
		int width = sourceImage.getWidth();
		int height = sourceImage.getHeight();
		int bigger = Math.max(width, height);
		int smaller = Math.min(width, height);
		double proportion = bigger / (double)smaller;
		biggerDimensionMax = (int) (proportion < 6.0?biggerDimensionMax+(proportion*25): 500);

		if (bigger >= biggerDimensionMax) {
			bigger = biggerDimensionMax;
			smaller = (int) (bigger / proportion);
			BufferedImage resized = ImageBytesUtil.resize(sourceImage, width > height ? bigger : smaller,height > width ? bigger : smaller);
			return resized;
		} else {
			return sourceImage;

		}

	}

}
