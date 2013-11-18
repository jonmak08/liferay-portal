/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.image;

import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageTool;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;

import java.io.File;
import java.io.RandomAccessFile;

import java.util.Arrays;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Shuyang Zhou
 * @author Sampsa Sohlman
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class ImageToolImplTest {

	@Before
	public void setUp() throws Exception {
		File file = getFile("liferay.gif");

		ImageBag imageBag = ImageToolUtil.read(file);

		image =  imageBag.getRenderedImage();
	}

	@Test
	public void testCropBottomRight() throws Exception {
		testCrop(
			image, image.getHeight() / 2, image.getWidth() / 2,
			image.getWidth() / 2, image.getHeight() / 2);
	}

	@Test
	public void testCropCenter() throws Exception {
		testCrop(
			image, image.getHeight() - (image.getHeight() / 2),
			image.getWidth() - (image.getWidth() / 2), image.getWidth() / 4,
			image.getHeight() / 4);
	}

	@Test
	public void testCropMoveUpperCornerDownAndRight() throws Exception {
		testCrop(
			image, image.getHeight(), image.getWidth(), image.getWidth() / 4,
			image.getHeight() / 4);
	}

	@Test
	public void testCropMoveUpperCornerUpAndLeft() throws Exception {
		testCrop(
			image, image.getHeight(), image.getWidth(), -(image.getWidth() / 4),
			-(image.getHeight() / 4));
	}

	@Test
	public void testCropSame() throws Exception {
		testCrop(image, image.getHeight(), image.getWidth(), 0, 0);
	}

	@Test
	public void testCropTopLeft() throws Exception {
		testCrop(
			image, image.getHeight() - (image.getHeight() / 2),
			image.getWidth() - (image.getWidth() / 2), 0, 0);
	}

	@Test
	public void testReadBMP() throws Exception {
		read("liferay.bmp");
	}

	@Test
	public void testReadGIF() throws Exception {
		read("liferay.gif");
	}

	@Test
	public void testReadJPEG() throws Exception {
		read("liferay.jpeg");
	}

	@Test
	public void testReadJPG() throws Exception {
		read("liferay.jpg");
	}

	@Test
	public void testReadPNG() throws Exception {
		read("liferay.png");
	}

	protected File getFile(String fileName) {
		fileName =
			"portal-impl/test/integration/com/liferay/portal/image/" +
				"dependencies/" + fileName;

		return new File(fileName);
	}

	protected void read(String fileName) throws Exception {
		File file = getFile(fileName);

		BufferedImage expectedImage = ImageIO.read(file);

		Assert.assertNotNull(expectedImage);

		Raster raster = expectedImage.getData();

		DataBuffer expectedDataBuffer = raster.getDataBuffer();

		String expectedType = FileUtil.getExtension(fileName);

		if (expectedType.equals("jpeg")) {
			expectedType = ImageTool.TYPE_JPEG;
		}

		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

		byte[] bytes = new byte[(int)randomAccessFile.length()];

		randomAccessFile.readFully(bytes);

		ImageBag imageBag = ImageToolUtil.read(bytes);

		RenderedImage resultImage = imageBag.getRenderedImage();

		Assert.assertNotNull(resultImage);

		raster = resultImage.getData();

		DataBuffer resultDataBuffer = raster.getDataBuffer();

		String resultType = imageBag.getType();

		Assert.assertTrue(
			StringUtil.equalsIgnoreCase(expectedType, resultType));

		if (expectedDataBuffer instanceof DataBufferByte) {
			DataBufferByte expectedDataBufferByte =
				(DataBufferByte)expectedDataBuffer;
			DataBufferByte resultDataBufferByte =
				(DataBufferByte)resultDataBuffer;

			Assert.assertTrue(
				Arrays.deepEquals(
					expectedDataBufferByte.getBankData(),
					resultDataBufferByte.getBankData()));
		}
		else if (expectedDataBuffer instanceof DataBufferInt) {
			DataBufferInt expectedDataBufferInt =
				(DataBufferInt)expectedDataBuffer;
			DataBufferInt resultDataBufferInt = (DataBufferInt)resultDataBuffer;

			Assert.assertTrue(
				Arrays.deepEquals(
					expectedDataBufferInt.getBankData(),
					resultDataBufferInt.getBankData()));
		}
		else {
			Assert.fail();
		}
	}

	protected void testCrop(
				RenderedImage image, int height, int width, int x, int y)
		throws Exception {

		RenderedImage croppedImage = ImageToolUtil.crop(
			image, height, width, x, y);

		int maxHeight = image.getHeight() - Math.abs(y);
		int maxWidth = image.getWidth() - Math.abs(x);

		Assert.assertEquals(
			croppedImage.getHeight(), Math.min(maxHeight, height));

		Assert.assertEquals(croppedImage.getWidth(), Math.min(maxWidth, width));
	}

	protected RenderedImage image;

}